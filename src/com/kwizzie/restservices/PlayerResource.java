package com.kwizzie.restservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.kwizzie.dao.LeaderBoardDAO;
import com.kwizzie.dao.PlayerDAO;
import com.kwizzie.model.Leader;
import com.kwizzie.model.Player;

import flexjson.JSONSerializer;

@Path("/player")
public class PlayerResource {

	@Autowired
	PlayerDAO playerDAO;
	
	@Autowired
	LeaderBoardDAO leaderDAO;
	
	@Autowired
	JSONSerializer serializer;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAll(){
		return serializer.deepSerialize(playerDAO.findAll());
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String authenticatePlayer(@QueryParam("username")String user, @QueryParam("password")String password){
		Player player = playerDAO.getPlayer(user);
		if(player!=null){
			if(player.getPassword().equals(password)){
				return serializer.deepSerialize(player);
				
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	@POST	
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	public String registerPlayer(@QueryParam("username")String userName, @QueryParam("password")String password,@QueryParam("name")String name,@QueryParam("emailID")String emailId ){
		if(!isPlayerExists(userName)){
			Player player = new Player(userName,password, name,emailId);
			playerDAO.save(player);	
			return "1";
		} else {
			return "0";
		}
	}

	@POST
	@Path("/updatePrivateScore")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateScore(@QueryParam("username")String username, @QueryParam("roomID")String roomID, @QueryParam("score")Integer score){
		Player player = playerDAO.getPlayer(username);
		if(player!=null){
			if(player.getQuizRoomScores().get(roomID) == null){
				player.getQuizRoomScores().put(roomID, score);
				updateLeaderboard(player, roomID);
				return "1";
			} 
		}
		return "0";
	}

	private void updateLeaderboard(Player player, String roomID) {
		Leader newPlayer = new Leader(player.getDetails().getName(), player.getUserName(), 
				player.getQuizRoomScores().get(roomID), player.getDetails().getPhotoUrl());
		List<Leader> leaders = leaderDAO.getLeaders(roomID);
		if(leaders == null){
			return;
		}
		int i=0;
		for(;i<leaders.size(); i++){
			Leader leader = leaders.get(i);
			if(leader.getScore() < newPlayer.getScore()){
				break;
			}
		}
		leaders.add(i, newPlayer);
		if(leaders.size() > 10){
			leaders.remove(10);
		}
		leaderDAO.updateLeaders(roomID, leaders);
		
	}

	@POST
	@Path("/updatePublicScore")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePublicScore(@QueryParam("username")String username, @QueryParam("category")String category, @QueryParam("score")Integer score){
		Player player = playerDAO.getPlayer(username);
		if(player!=null){
			if(player.getPublicRoomScores().get(category) == null){
				Integer currentScore = player.getPublicRoomScores().get(category);
				if(currentScore == null){
					currentScore = 0;
				}
				player.getPublicRoomScores().put(category, currentScore + score);
				updatePublicLeaderboard(player, category);
				return "1";
			} 
		}
		return "0";
		
	}
	
	
	private void updatePublicLeaderboard(Player player, String category) {
		Leader newPlayer = new Leader(player.getDetails().getName(), player.getUserName(), 
				player.getPublicRoomScores().get(category), player.getDetails().getPhotoUrl());
		List<Leader> leaders = leaderDAO.getLeaders("public");
		if(leaders == null){
			leaders = new ArrayList<>();
		}
		int i=0;
		for(;i<leaders.size(); i++){
			Leader leader = leaders.get(i);
			if(leader.getScore() < newPlayer.getScore()){
				break;
			}
		}
		leaders.add(i, newPlayer);
		if(leaders.size() > 10){
			leaders.remove(10);
		}
		leaderDAO.updateLeaders("public", leaders);
	}

	private boolean isPlayerExists(String username){
		Player player = playerDAO.get(username);
		if(player == null){
			return false;
		} else {
			return true;
		}
	}
}
