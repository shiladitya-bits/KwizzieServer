package com.kwizzie.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.kwizzie.model.PublicQuizRoom;
import com.kwizzie.model.Question;
import com.mongodb.Mongo;

public class PublicQuizRoomDAO extends BasicDAO<PublicQuizRoom, String>{

	private int NO_OF_QUESTIONS_TO_BE_SELECTED=10;

	protected PublicQuizRoomDAO(Mongo mongo, Morphia morphia, String dbName) {
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
		while(i<NO_OF_QUESTIONS_TO_BE_SELECTED && i<allQuestions.size()){
			int questionNum=(int)(Math.random())*(allQuestions.size());
			selectedQuestions.add(allQuestions.get(questionNum));
			allQuestions.remove(questionNum);
			i++;
		}
		return selectedQuestions;
	}
	
	public void addQuestion(String category, Question q){
		List<Question> selectedQuestions=new ArrayList<Question>();
		PublicQuizRoom quizRoom = ds.find(PublicQuizRoom.class).asList().get(0);
		if(quizRoom== null){
			return;
		}
		List<Question> allQuestions = quizRoom.getCategoryQuestionMap().get(category);
		if(allQuestions == null){
			quizRoom.getCategoryQuestionMap().put(category, new ArrayList<Question>());
		}
		quizRoom.getCategoryQuestionMap().get(category).add(q);
		ds.save(quizRoom);
	}
}
