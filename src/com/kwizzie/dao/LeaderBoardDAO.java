package com.kwizzie.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.kwizzie.model.Leader;
import com.kwizzie.model.LeaderBoard;
import com.mongodb.Mongo;

public class LeaderBoardDAO extends BasicDAO<LeaderBoard,String> {

	protected LeaderBoardDAO(Mongo mongo, Morphia morphia, String dbName) {
		super(mongo, morphia, dbName);
	}

	public List<Leader> getLeaders(String roomID){
		Query<LeaderBoard> query= ds.createQuery(LeaderBoard.class).field("quizRoomCode").equal(roomID);
		List<LeaderBoard> results = query.asList();
		if(results.isEmpty()){
			return null;
		}
		LeaderBoard leaderBoard = results.get(0);
		List<Leader> leaders = leaderBoard.getLeaders();
		if(leaders == null){
			leaders = new ArrayList<>();
		}
		return leaders;
	}

	public void updateLeaders(String roomID, List<Leader> leaders) {	
		Query<LeaderBoard> query= ds.createQuery(LeaderBoard.class).field("quizRoomCode").equal(roomID);
		List<LeaderBoard> results = query.asList();
		LeaderBoard leaderBoard;
		if(results.isEmpty()){
			leaderBoard = new LeaderBoard(leaders, roomID);
		} else {
			leaderBoard = query.asList().get(0);
			leaderBoard.setLeaders(leaders);
		}
		ds.save(leaderBoard);
	}
}
