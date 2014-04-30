package com.kwizzie.restservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPlayer(@PathParam("username") String username){
		Player player = playerDAO.getPlayer(username);
		if(player != null){
			return serializer.deepSerialize(player);
		} else {
			return "";
		}
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String authenticatePlayer(@FormParam("username")String user, @FormParam("password")String password){
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
	public String registerPlayer(@FormParam("username")String userName, @FormParam("password")String password,@FormParam("name")String name,@FormParam("emailID")String emailId ){
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
	public String updateScore(@FormParam("username")String username, @FormParam("roomID")String roomID, @FormParam("score")Integer score){
		Player player = playerDAO.getPlayer(username);
		if(player!=null){
				//TODO: add check to see if player has already played in this room
				player.getQuizRoomScores().put(roomID, score);
				playerDAO.save(player);
				updateLeaderboard(player, roomID);
				return "1";
		}
		return "0";
	}

	private void updateLeaderboard(Player player, String roomID) {
		Leader newPlayer = new Leader(player.getDetails().getName(), player.getUserName(), 
				player.getQuizRoomScores().get(roomID), player.getDetails().getPhotoUrl());
		List<Leader> leaders = leaderDAO.getLeaders(roomID);
		if(leaders == null){
			leaders = new ArrayList<>();
		}
		int i=0;
		for(i=0;i<leaders.size();i++){
			if(leaders.get(i).getUsername().equals(newPlayer.getUsername())){
				break;
			}
		}
		if(i<leaders.size()){
			leaders.remove(i);
		}
		for(i=0;i<leaders.size(); i++){
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
	public String updatePublicScore(@FormParam("username")String username, @FormParam("category")String category, @FormParam("score")Integer score){
		Player player = playerDAO.getPlayer(username);
		if(player!=null){
			Integer currentScore = player.getPublicRoomScores().get(category);
			if(currentScore == null){
				currentScore = 0;
			}
			player.getPublicRoomScores().put(category, currentScore + score);
			playerDAO.save(player);
			updatePublicLeaderboard(player);
			
			return "1";
		}
		return "0";
	}
	
	private void updatePublicLeaderboard(Player player) {
		Leader newPlayer = new Leader(player.getDetails().getName(), player.getUserName(), 
				getTotalPublicScore(player.getPublicRoomScores()), player.getDetails().getPhotoUrl());
		List<Leader> leaders = leaderDAO.getLeaders("public");
		if(leaders == null){
			leaders = new ArrayList<>();
		}
		int i=0;
		for(i=0;i<leaders.size();i++){
			if(leaders.get(i).getUsername().equals(newPlayer.getUsername())){
				break;
			}
		}
		if(i<leaders.size()){
			leaders.remove(i);
		}

		for(i=0;i<leaders.size(); i++){
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

	private int getTotalPublicScore(Map<String, Integer> scores){
		int total = 0;
		for(Map.Entry<String, Integer> score : scores.entrySet()){
			total += score.getValue();
		}
		return total;
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
