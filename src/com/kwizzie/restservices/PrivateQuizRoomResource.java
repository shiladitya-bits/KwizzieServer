package com.kwizzie.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.kwizzie.dao.PrivateQuizRoomDAO;

@Path("/quizRoom/private")
public class PrivateQuizRoomResource {

	@Autowired
	PrivateQuizRoomDAO quizRoomDAO;
	
	@Autowired
	Gson gson;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String enterQuizRoom(@QueryParam("room") String roomID , @QueryParam("key") String securityKey){
		return gson.toJson(quizRoomDAO.getQuizRoom(roomID, securityKey));
	}
}
