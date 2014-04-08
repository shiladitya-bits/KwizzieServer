package com.kwizzie.model;

import java.util.List;

import com.google.code.morphia.annotations.Id;

public class LeaderBoard {
	
	@Id
	private String roomCode;
	private List<Player> leaders;

	public LeaderBoard(){
	}

	public LeaderBoard(String roomCode, List<Player> leaders) {
		super();
		this.roomCode = roomCode;
		this.leaders = leaders;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public List<Player> getLeaders() {
		return leaders;
	}

	public void setLeaders(List<Player> leaders) {
		this.leaders = leaders;
	}
}
