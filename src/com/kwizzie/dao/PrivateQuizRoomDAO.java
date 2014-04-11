package com.kwizzie.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.kwizzie.model.Player;
import com.kwizzie.model.PrivateQuizRoom;
import com.mongodb.Mongo;

public class PrivateQuizRoomDAO extends BasicDAO<PrivateQuizRoom , String>{

	protected PrivateQuizRoomDAO(Mongo mongo, Morphia morphia, String dbName) {
		super(mongo, morphia, dbName);
	}
	
	public PrivateQuizRoom getQuizRoom(String roomID,String securityKey){
		Query<PrivateQuizRoom> query = ds.createQuery(PrivateQuizRoom.class).field("roomID").equal(roomID);
		PrivateQuizRoom quizRoom = query.asList().get(0);
		if(quizRoom!=null && quizRoom.getSecurityKey().equals(securityKey)){
			return quizRoom;
		}
		return null;
		
	}
}
