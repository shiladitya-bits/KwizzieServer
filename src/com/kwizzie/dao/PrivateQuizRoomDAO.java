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
	
	public PrivateQuizRoom getPrivateQuizRoom(String id, String password){
		Query<PrivateQuizRoom> query= ds.createQuery(PrivateQuizRoom.class).field("room_id").equal(id).field("password").equal(password);
		return query.asList().get(0);
	}
}
