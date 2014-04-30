package com.kwizzie.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.kwizzie.model.PrivateQuizRoom;
import com.kwizzie.model.Question;
import com.mongodb.Mongo;

public class PrivateQuizRoomDAO extends BasicDAO<PrivateQuizRoom , String>{

	public PrivateQuizRoomDAO(Mongo mongo, Morphia morphia, String dbName) {
		super(mongo, morphia, dbName);
	}
	
	public PrivateQuizRoom getQuizRoom(String roomID,String securityKey){
		Query<PrivateQuizRoom> query = ds.createQuery(PrivateQuizRoom.class).field("roomID").equal(roomID);
		List<PrivateQuizRoom> quizRoomList = query.asList();
		if(quizRoomList.isEmpty()){
			return null;
		} else {
			PrivateQuizRoom quizRoom = quizRoomList.get(0);
			if(quizRoom!=null && quizRoom.getSecurityKey().equals(securityKey)){
				return quizRoom;
			}
			return null;
		}
	}
	
	public void addQuestionToRoom(String roomID, Question q){
		Query<PrivateQuizRoom> query = ds.createQuery(PrivateQuizRoom.class).field("roomID").equal(roomID);
		List<PrivateQuizRoom> quizRoomList = query.asList();
		if(!quizRoomList.isEmpty()){
			PrivateQuizRoom quizRoom = quizRoomList.get(0);
			List<Question> questions = quizRoom.getQuestions();
			if(questions == null){
				quizRoom.setQuestions(new ArrayList<Question>());
			}
			quizRoom.getQuestions().add(q);
			ds.save(quizRoom);
		}

	}
}
