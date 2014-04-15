package com.kwizzie.restservices;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.kwizzie.dao.PublicQuizRoomDAO;
import com.kwizzie.dao.QuestionCategoryDAO;
import com.kwizzie.model.Question;
import com.kwizzie.model.QuestionCategory;

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
	@Produces(MediaType.TEXT_PLAIN)
	public String addCategory(@FormParam("categoryCode")String categoryCode, @FormParam("categoryName") String categoryName){
		QuestionCategory category = questionCategoryDAO.get(categoryCode);
		if(category == null){
			questionCategoryDAO.save(new QuestionCategory(categoryCode, categoryName));
		}
		return serializer.deepSerialize(questionCategoryDAO.getCategories());
	}
	
	@GET
	@Path("/CategoryQuestions")
	@Produces(MediaType.TEXT_PLAIN)
	public String getQuestions(@QueryParam("category")String category){
		return serializer.deepSerialize(quizRoomDAO.getQuestions(category));
		
	}

}
