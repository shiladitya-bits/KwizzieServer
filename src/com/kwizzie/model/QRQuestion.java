package com.kwizzie.model;

public class QRQuestion extends Question{

	public QRQuestion( QuestionLocation location,
			QuestionCategory category, String questionTitle,
			AnswerType answerType, boolean isLocked) {
		super();
		this.location = location;
		this.category = category;
		this.questionTitle = questionTitle;
		this.answerType = answerType;
		this.isLocked = isLocked;
	}
}
