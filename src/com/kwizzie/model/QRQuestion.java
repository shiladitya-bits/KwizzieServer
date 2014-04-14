package com.kwizzie.model;

public class QRQuestion extends Question{

	public QRQuestion(){
		
	}
	public QRQuestion( QuestionLocation location,
			String questionTitle,
			AnswerType answerType, boolean isLocked) {
		super();
		this.location = location;
		this.questionTitle = questionTitle;
		this.answerType = answerType;
		this.isLocked = isLocked;
		this.typeOfQuestion="QR_QUESTION";
	}
}
