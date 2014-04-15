package com.kwizzie.model;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Id;

public class LeaderBoard {
	@Embedded
	private List<Leader> leaders;
	@Id
	private String quizRoomCode;
	
	public LeaderBoard(){
		this.leaders = new ArrayList<Leader>();
	}
	
	public LeaderBoard(List<Leader> leaders, String quizRoomCode) {
		super();
		this.leaders = leaders;
		this.quizRoomCode = quizRoomCode;
	}

	public List<Leader> getLeaders() {
		return leaders;
	}
	public void setLeaders(List<Leader> leaders) {
		this.leaders = leaders;
	}
	public String getQuizRoomCode() {
		return quizRoomCode;
	}
	public void setQuizRoomCode(String quizRoomCode) {
		this.quizRoomCode = quizRoomCode;
	}

}
