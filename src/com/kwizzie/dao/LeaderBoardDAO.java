package com.kwizzie.dao;

import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.kwizzie.model.LeaderBoard;
import com.kwizzie.model.Player;
import com.mongodb.Mongo;

public class LeaderBoardDAO extends BasicDAO<LeaderBoard,String> {

	protected LeaderBoardDAO(Mongo mongo, Morphia morphia, String dbName) {
		super(mongo, morphia, dbName);
	}

	public List<LeaderBoard> getLeaders(){
		return ds.find(LeaderBoard.class).asList();
	}
}
