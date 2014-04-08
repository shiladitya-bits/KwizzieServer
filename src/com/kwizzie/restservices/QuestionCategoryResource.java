package com.kwizzie.restservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.kwizzie.dao.QuestionCategoryDAO;
import com.kwizzie.model.QuestionCategory;

@Path("/category")
public class QuestionCategoryResource {
	
	@Autowired
	QuestionCategoryDAO questionCategoryDAO;
	
	@Autowired
	Gson gson;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAll(){
		List<QuestionCategory> questions = questionCategoryDAO.getCategories();
		return gson.toJson(questions);
	}
}
