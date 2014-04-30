package com.kwizzie.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.kwizzie.model.PublicQuizRoom;
import com.kwizzie.model.Question;
import com.mongodb.Mongo;

public class PublicQuizRoomDAO extends BasicDAO<PublicQuizRoom, String>{

	private int NO_OF_QUESTIONS_TO_BE_SELECTED=10;

	public PublicQuizRoomDAO(Mongo mongo, Morphia morphia, String dbName) {
		super(mongo, morphia, dbName);
	}

	public List<Question> getQuestions(String category){
		List<Question> selectedQuestions=new ArrayList<Question>();
		PublicQuizRoom quizRoom = ds.find(PublicQuizRoom.class).asList().get(0);
		if(quizRoom== null){
			return null;
		}
		List<Question> allQuestions = quizRoom.getCategoryQuestionMap().get(category);
		if(allQuestions == null){
			return null;
		}
		int i=0;
		int size = allQuestions.size();
		Random random = new Random();
		while(i<NO_OF_QUESTIONS_TO_BE_SELECTED && i<size){
			//int questionNum=(int)(Math.random())*(allQuestions.size());
			int questionNum = random.nextInt(allQuestions.size());
			selectedQuestions.add(allQuestions.get(questionNum));
			allQuestions.remove(questionNum);
			i++;
		}
		return selectedQuestions;
		
	}
	
	public void addQuestion(String category, Question q){
		List<Question> selectedQuestions=new ArrayList<Question>();
		List<PublicQuizRoom> quizRoomList = ds.find(PublicQuizRoom.class).asList();
		PublicQuizRoom quizRoom = null;
		if(quizRoomList.isEmpty()){
			quizRoom = new PublicQuizRoom();
		} else {
				quizRoom = quizRoomList.get(0);
		}
		List<Question> allQuestions = quizRoom.getCategoryQuestionMap().get(category);
		if(allQuestions == null){
			quizRoom.getCategoryQuestionMap().put(category, new ArrayList<Question>());
		}
		quizRoom.getCategoryQuestionMap().get(category).add(q);
		ds.save(quizRoom);
	}
}
