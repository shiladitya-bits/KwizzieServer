package com.kwizzie.dao;

import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.kwizzie.model.PublicQuizRoom;
import com.kwizzie.model.Question;
import com.mongodb.Mongo;

public class PublicQuizRoomDAO extends BasicDAO<PublicQuizRoom, String>{

	protected PublicQuizRoomDAO(Mongo mongo, Morphia morphia, String dbName) {
		super(mongo, morphia, dbName);
	}

	public PublicQuizRoom getQuestions(String category){
		PublicQuizRoom room = ds.find(PublicQuizRoom.class).asList().get(0);
		return room;
	}
}
