package com.kwizzie.model;

public class TextAnswerType implements AnswerType {
	
	private String correctAnswer;
	
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	@Override
	public boolean checkAnswer(Object answer) {
		String enteredAns = (String) answer;
		if(enteredAns.equalsIgnoreCase(correctAnswer)){
			return true;
		}
		return false;
	}

}
