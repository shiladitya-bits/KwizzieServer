package com.kwizzie.restservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.kwizzie.dao.PublicQuizRoomDAO;
import com.kwizzie.dao.QuestionCategoryDAO;
import com.kwizzie.model.AudioQuestion;
import com.kwizzie.model.MCQAnswerType;
import com.kwizzie.model.PictureQuestion;
import com.kwizzie.model.PublicQuizRoom;
import com.kwizzie.model.QRAnswerType;
import com.kwizzie.model.QRQuestion;
import com.kwizzie.model.Question;
import com.kwizzie.model.QuestionCategory;
import com.kwizzie.model.TextAnswerType;
import com.kwizzie.model.TextQuestion;
import com.kwizzie.model.VideoQuestion;

import flexjson.JSONSerializer;

@Path("/quizRoom/public")
public class PublicQuizRoomResource {
	
	@Autowired
	QuestionCategoryDAO questionCategoryDAO;
	
	@Autowired
	PublicQuizRoomDAO quizRoomDAO;
	
	@Autowired
	JSONSerializer serializer;
	
	@GET
	@Path("/categories")
	public String getCategories(){
		return serializer.deepSerialize(questionCategoryDAO.getCategories());
	}
	
	@POST
	@Path("/categories/add")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCategory(@FormParam("categoryCode")String categoryCode, @FormParam("categoryName") String categoryName){
		QuestionCategory category = questionCategoryDAO.get(categoryCode);
		if(category == null){
			questionCategoryDAO.save(new QuestionCategory(categoryCode, categoryName));
		}
		return serializer.deepSerialize(questionCategoryDAO.getCategories());
	}

	@GET
	@Path("/categories/add")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCategoryGet(@QueryParam("categoryCode")String categoryCode, @QueryParam("categoryName") String categoryName){
		QuestionCategory category = questionCategoryDAO.get(categoryCode);
		if(category == null){
			questionCategoryDAO.save(new QuestionCategory(categoryCode, categoryName));
		}
		return serializer.deepSerialize(questionCategoryDAO.getCategories());
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getQuestions(@QueryParam("category")String category){
		List<Question> questions = quizRoomDAO.getQuestions(category);
		if(questions != null){
			return serializer.deepSerialize(questions);
		} else return "";
	}

	@GET
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	public String addQuestions(@QueryParam("category")String categoryCode){
		List<Question> questions = new ArrayList<>();
		ArrayList<String> optionList = new ArrayList<String>();
		optionList.add("disney");
		optionList.add("dreamworks");
		Question ques = new PictureQuestion("http://img4.wikia.nocookie.net/__cb20130222060253/disney/images/7/7d/2013disneyprincess.jpg",null,
		"Identify The picture",new MCQAnswerType(optionList, 0),false);

		Question ques2 = new TextQuestion("contin___s",null,"Fill in the missing characters",new TextAnswerType("uou"),false);
		ArrayList<String> options3 = new ArrayList<String>();
		options3.add("sonu nigam");
		options3.add("arijit singh");
		options3.add("mika singh");
		options3.add("honney singh");

		Question ques3 = new AudioQuestion("http://www.largesound.com/ashborytour/sound/brobob.mp3", null, "Identify the singer", new MCQAnswerType(options3, 2), false);
		Question ques4 = new VideoQuestion("http://info.sonicretro.org/images/5/54/Tiger_Sonic_3D_Blast_Ad.flv", null, "Identify the actor in video", new TextAnswerType("Ranbeer Kapoor"), false);
		Question ques5 = new QRQuestion(null, "scan the qr code", new QRAnswerType("testqr"), false);
		questions=new ArrayList<Question>();
	//	questions.add(ques5);
		questions.add(ques);
		questions.add(ques2);
		questions.add(ques3);
		questions.add(ques4);
		
		PublicQuizRoom quizRoom = quizRoomDAO.get("public");
		if(quizRoom == null){
			quizRoom = new PublicQuizRoom();
		}
		Map<String , List<Question>> categoryQuesMap = quizRoom.getCategoryQuestionMap();
		categoryQuesMap.put(categoryCode, questions);
		try{
			quizRoomDAO.save(quizRoom);
			return "1";
		} catch(Exception e){
			return "0";
		}
	}
	@POST
	@Path("/addQuestion/{category}/text")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String addTextQuestion(@PathParam("category")String category, 
			@FormParam("quesTitle")String questionTitle, @FormParam("quesSubtitle")String subTitle){
		
		//TODO: location and answerType
		Question q = new TextQuestion(subTitle, null, questionTitle, null, false);
		quizRoomDAO.addQuestion(category, q);
		return "1";
	}
	
	@POST
	@Path("/addQuestion/{category}/qr")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String addQrQuestion(@PathParam("category")String category, 
			@FormParam("quesTitle")String questionTitle, @FormParam("quesSubtitle")String subTitle){

		//TODO: location and answerType
		Question q = new QRQuestion(null, questionTitle, null, false);
		quizRoomDAO.addQuestion(category, q);
		return "1";
	}
	
/*	@POST
	@Path("/addQuestion/{roomID}/picture")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPictureQuestion(@PathParam("roomID")String roomID, 
			@FormDataParam("quesTitle")String quesTitle){
		return "1";
	}
*/
}