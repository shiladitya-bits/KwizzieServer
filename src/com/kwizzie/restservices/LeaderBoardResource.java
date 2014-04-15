package com.kwizzie.restservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.kwizzie.dao.LeaderBoardDAO;
import com.kwizzie.model.Leader;

import flexjson.JSONSerializer;


@Path("/leaders")
public class LeaderBoardResource {

	@Autowired
	LeaderBoardDAO leaderBoardDAO;
	
	@Autowired
	JSONSerializer serializer;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getLeaders(@QueryParam("roomID")String roomID){
		
		List<Leader> leaders = leaderBoardDAO.getLeaders(roomID);
		if(leaders != null){
			return serializer.deepSerialize(leaders);
		}
		return "";
	}
	
}
