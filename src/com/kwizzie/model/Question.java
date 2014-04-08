package com.kwizzie.model;

public abstract class Question {
	protected String questionId;
	protected QuestionLocation location;
	protected QuestionCategory category;
	protected String questionTitle;
	protected AnswerType answerType;
	protected boolean isLocked;
	public QuestionLocation getLocation() {
		return location;
	}
	public void setLocation(QuestionLocation location) {
		this.location = location;
	}
	public QuestionCategory getCategory() {
		return category;
	}
	public void setCategory(QuestionCategory category) {
		this.category = category;
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
	
	
}
