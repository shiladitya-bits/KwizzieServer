package com.kwizzie.model;

import com.google.code.morphia.annotations.Embedded;

@Embedded
public abstract class Question {

	@Embedded
	protected QuestionLocation location;
	protected String questionTitle;
	
	@Embedded
	protected AnswerType answerType;
	protected boolean isLocked;
	protected String typeOfQuestion; 
	
	public QuestionLocation getLocation() {
		return location;
	}
	public void setLocation(QuestionLocation location) {
		this.location = location;
	}
	
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public AnswerType getAnswerType() {
		return answerType;
	}
	public void setAnswerType(AnswerType answerType) {
		this.answerType = answerType;
	}
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	public String getTypeOfQuestion() {
		return typeOfQuestion;
	}
	public void setTypeOfQuestion(String typeOfQuestion) {
		this.typeOfQuestion = typeOfQuestion;
	}
}
