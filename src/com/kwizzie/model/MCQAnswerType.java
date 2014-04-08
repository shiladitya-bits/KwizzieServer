package com.kwizzie.model;

import java.util.List;

public class MCQAnswerType implements AnswerType {

	private List<String> options;
	private int correctOptionIndex;
	
	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public int getCorrectOptionIndex() {
		return correctOptionIndex;
	}

	public void setCorrectOptionIndex(int correctOptionIndex) {
		this.correctOptionIndex = correctOptionIndex;
	}

	public MCQAnswerType(List<String> options, int correctOption){
		this.options = options;
		this.correctOptionIndex=correctOption;
	}
	
	@Override
	public boolean checkAnswer(Object answer) {
		int selectedOption = (Integer) answer;
		if(correctOptionIndex==selectedOption){
			return true;
		}
		return false;
		
	}

}
