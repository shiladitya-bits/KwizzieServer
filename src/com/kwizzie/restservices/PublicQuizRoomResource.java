package com.kwizzie.restservices;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.kwizzie.dao.PublicQuizRoomDAO;
import com.kwizzie.dao.QuestionCategoryDAO;
import com.kwizzie.model.QuestionCategory;

@Path("/quizRoom/public")
public class PublicQuizRoomResource {
	
	@Autowired
	QuestionCategoryDAO questionCategoryDAO;
	
	@Autowired
	PublicQuizRoomDAO quizRoomDAO;
	
	@Autowired
	Gson gson;
	@GET
	@Path("/categories")
	public String getCategories(){
		return gson.toJson(questionCategoryDAO.getCategories());
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addCategory(@FormParam("categoryCode")String categoryCode, @FormParam("categoryName") String categoryName){
		QuestionCategory category = questionCategoryDAO.get(categoryCode);
		if(category == null){
			questionCategoryDAO.save(new QuestionCategory(categoryCode, categoryName));
		}
		return gson.toJson(questionCategoryDAO.getCategories());
	}
	
	@GET
	@Path("/CategoryQuestions")
	@Produces(MediaType.TEXT_PLAIN)
	public String getQuestions(@QueryParam("category")String category){
		return quizRoomDAO.getQuestions(category).toString();
	}

}
