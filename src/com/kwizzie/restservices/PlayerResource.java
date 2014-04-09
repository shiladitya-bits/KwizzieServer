package com.kwizzie.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.kwizzie.dao.PlayerDAO;
import com.kwizzie.model.Player;

@Path("/player")
public class PlayerResource {

	@Autowired
	PlayerDAO playerDAO;
	
	@Autowired
	Gson gson;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAll(){
		return gson.toJson(playerDAO.findAll());
	}
	
	@POST
	@Path("/authenticate")
	@Produces(MediaType.TEXT_PLAIN)
	public String authenticatePlayer(@QueryParam("user")String user, @QueryParam("password")String password){
		Player player = playerDAO.getPlayer(user);
		if(player!=null){
			if(player.getPassword().equals(password)){
				return gson.toJson(player);
			} else {
				return "1";
			}
		} else {
			return "0";
		}
	}
//	@POST	
//	public void registerPlayer(String userName, String password,String name,String emailId ){
//		Player player = new Player(userName,password, name,emailId);
//		pd.save(player);
//	}
}
