package com.kwizzie.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/leaders")
public class LeaderBoardResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getLeaders(){
		return "";
	}
	
//	public void updateLeaders(Player player){
//	//TODO compare new score of player with minimum score of leaders and update leaders here
//}
}
