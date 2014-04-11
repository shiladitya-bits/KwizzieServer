package com.kwizzie.restservices;

import com.kwizzie.model.AnswerType;

public class QRAnswerType implements AnswerType{

	String correctAnswer;
	
	public QRAnswerType(){
	}
	public QRAnswerType(String correctAnswer){
		this.correctAnswer = correctAnswer;
	}
	@Override
	public boolean checkAnswer(Object answer) {
		return false;
	}

}
