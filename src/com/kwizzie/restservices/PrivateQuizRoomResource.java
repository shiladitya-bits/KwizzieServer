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

import com.kwizzie.dao.PlayerDAO;
import com.kwizzie.dao.PrivateQuizRoomDAO;
import com.kwizzie.model.AnswerType;
import com.kwizzie.model.AudioQuestion;
import com.kwizzie.model.MCQAnswerType;
import com.kwizzie.model.PictureQuestion;
import com.kwizzie.model.Player;
import com.kwizzie.model.PrivateQuizRoom;
import com.kwizzie.model.QRAnswerType;
import com.kwizzie.model.QRQuestion;
import com.kwizzie.model.Question;
import com.kwizzie.model.QuestionLocation;
import com.kwizzie.model.TextAnswerType;
import com.kwizzie.model.TextQuestion;
import com.kwizzie.model.VideoQuestion;

import flexjson.JSONSerializer;

@Path("/quizRoom/private")
public class PrivateQuizRoomResource {

	@Autowired
	PrivateQuizRoomDAO quizRoomDAO;
	
	@Autowired
	JSONSerializer serializer;
	
	@Autowired
	PlayerDAO playerDAO;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String enterQuizRoom(@QueryParam("roomId") String roomID , @QueryParam("key") String securityKey, @QueryParam("player")String player){
		if((player != null && player.length()!=0)){
			Player playerObj = playerDAO.getPlayer(player);
			Map<String, Integer>  scores = playerObj.getQuizRoomScores();
			if(scores != null){
				if(scores.containsKey(roomID)){
					return "2";
				}
			} 
		}
		PrivateQuizRoom quizRoom = quizRoomDAO.getQuizRoom(roomID, securityKey);
		if(quizRoom != null){
			return serializer.deepSerialize(quizRoom);
		} else {
			return "1";
		}	
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	public String addQuizRoomPost(@FormParam("roomID")String roomID, @FormParam("roomName")String roomName, @FormParam("key")String securityKey, 
			@FormParam("description")String description){
		PrivateQuizRoom quizRoom = new PrivateQuizRoom(new ArrayList<Question>(), securityKey, description, roomName, roomID);
		quizRoomDAO.save(quizRoom);
		return "1";
	}
	
	@POST
	@Path("/addQuestion/{roomID}/text")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String addTextQuestion(@PathParam("roomID")String roomID, 
			@FormParam("quesTitle")String questionTitle, @FormParam("quesSubtitle")String subTitle,
			@FormParam("latitude")String latitude, @FormParam("longitude")String longitude, @FormParam("destinationInfo")String locationName,
			@FormParam("answerType")String answerTypeString, @FormParam("optiona")String option1, @FormParam("optionb")String option2, @FormParam("optionc")String option3, @FormParam("optiond")String option4, @FormParam("options")String correctOption,
			@FormParam("correctAns")String correctAns){
		
		//location
    	QuestionLocation location = null;
    	if(checkLocationInput(latitude, longitude, locationName)){
    		location = new QuestionLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1000, locationName, "");
    	}

    	//answer type
    	AnswerType answerType =null;
    	if("mcq".equals(answerTypeString)){
    		answerType = new MCQAnswerType(getAnswerList(option1, option2, option3, option4), Integer.parseInt(correctOption));
    	} else if("text".equals(answerTypeString)){
    			answerType = new TextAnswerType(correctAns);
    	}

    	Question q = new TextQuestion(subTitle, location, questionTitle, answerType, false);
		quizRoomDAO.addQuestionToRoom(roomID, q);
		return "1";
	}
	
    private List<String> getAnswerList(String option1, String option2,
			String option3, String option4) {
    	List<String> options = new ArrayList<String>();
    	if(option1!=null && option1.trim()!=""){
    		options.add(option1);
    	}
     	if(option2!=null && option2.trim()!=""){
    		options.add(option2);
    	}
     	if(option3!=null && option3.trim()!=""){
    		options.add(option3);
    	}
     	if(option4!=null && option4.trim()!=""){
    		options.add(option4);
    	}
    	
		return options;
	}

	private boolean checkLocationInput(String latitude, String longitude, String name) {
    	if(latitude==null || longitude==null || name==null){
    		return false;
    	}
    	if(latitude.trim()=="" || longitude.trim()=="" || name.trim() ==""){
    		return false;
    	}
    	return true;
	}
	
	@POST
	@Path("/addQuestion/{roomID}/qr")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String addQrQuestion(@PathParam("roomID")String roomID, 
			@FormParam("quesTitle")String questionTitle,
			@FormParam("latitude")String latitude, @FormParam("longitude")String longitude, @FormParam("destinationInfo")String locationName,
			@FormParam("correctAns")String correctAns){

		//location
    	QuestionLocation location = null;
    	if(checkLocationInput(latitude, longitude, locationName)){
    		location = new QuestionLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1000, locationName, "");
    	}

    	//answer type
    	
    	AnswerType answerType = new QRAnswerType(correctAns);

    	Question q = new QRQuestion(location, questionTitle, answerType, false);
		quizRoomDAO.addQuestionToRoom(roomID, q);
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
/*	@GET
	@Path("/add")
	public String addQuizRoom(@QueryParam("roomId")String roomID,@QueryParam("key")String securityKey){
		List<Question> questions = new ArrayList<>();
		ArrayList<String> optionList = new ArrayList<String>();
		optionList.add("disney");
		optionList.add("dreamworks");
		QuestionLocation location = new QuestionLocation(15.390353333333334, 73.87886499999999,1000.0, "BITS Goa", "http://localhost:8080/images/faqsImage.png");
		Question ques = new PictureQuestion("http://img4.wikia.nocookie.net/__cb20130222060253/disney/images/7/7d/2013disneyprincess.jpg", location,
		"Identify The picture",new MCQAnswerType(optionList, 0),false);

		Question ques2 = new TextQuestion("contin___s", null,"Fill in the missing characters",new TextAnswerType("uou"),false);
		ArrayList<String> options3 = new ArrayList<String>();
		options3.add("sonu nigam");
		options3.add("arijit singh");
		options3.add("mika singh");
		options3.add("honney singh");

		Question ques3 = new AudioQuestion("http://www.largesound.com/ashborytour/sound/brobob.mp3", location, "Identify the singer", new MCQAnswerType(options3, 2), false);
		Question ques4 = new VideoQuestion("http://info.sonicretro.org/images/5/54/Tiger_Sonic_3D_Blast_Ad.flv", location, "Identify the actor in video", new TextAnswerType("Ranbeer Kapoor"), false);
		Question ques5 = new QRQuestion(location, "scan the qr code", new QRAnswerType("testqr"), false);
		questions=new ArrayList<Question>();
		questions.add(ques5);
		questions.add(ques);
		questions.add(ques2);
		questions.add(ques3);
		questions.add(ques4);
		try{
		//TODO: change roomName and description
		quizRoomDAO.save(new PrivateQuizRoom(questions, securityKey, roomID, roomID, roomID));
		return "1";
		} catch(Exception e){
			return "0";
		}
	}*/
	
	@GET
	@Path("/delete")
	public String deleteQuizRoom(@QueryParam("roomId")String roomID,@QueryParam("key")String securityKey){
		PrivateQuizRoom quizRoom = quizRoomDAO.getQuizRoom(roomID, securityKey);
		quizRoomDAO.delete(quizRoom);
		return "1";
	}
}
