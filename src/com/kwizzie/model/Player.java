package com.kwizzie.model;

import java.util.HashMap;
import java.util.Map;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public class Player {
	
	@Id
	private String userName;
	
	private String password;
	
	@Embedded
	private PlayerPersonalDetails details;

	private Map<String,Integer> quizRoomScores;
	
	private Map<String, Integer> publicRoomScores;

	private static final String DEFAULT_PHOTO_URL = "localhost:8080/KwizzieServer/kwizzieMedia/user_default.png";
	
	public Player(){
		quizRoomScores = new HashMap<>();
		publicRoomScores = new HashMap<>();
	}
	
	public Player(String userName, String password,String photoURL,String name,String emailId) {
		details= new PlayerPersonalDetails(photoURL,name,emailId);
		this.userName = userName;
		this.password = password;
		quizRoomScores = new HashMap<>();
		publicRoomScores = new HashMap<>();
	}

	public Player(String userName, String password,String name,String emailId) {
		this(userName, password, DEFAULT_PHOTO_URL, name, emailId);
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PlayerPersonalDetails getDetails() {
		return details;
	}

	public void setDetails(PlayerPersonalDetails details) {
		this.details = details;
	}

	public Map<String, Integer> getQuizRoomScores() {
		return quizRoomScores;
	}

	public void setQuizRoomScores(Map<String, Integer> quizRoomScores) {
		this.quizRoomScores = quizRoomScores;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Integer> getPublicRoomScores() {
		return publicRoomScores;
	}

	public void setPublicRoomScores(Map<String, Integer> publicRoomScores) {
		this.publicRoomScores = publicRoomScores;
	}

	@Override
	public String toString() {
		return "Player [userName=" + userName + ", password=" + password
				+ ", details=" + details + ", quizRoomScores=" + quizRoomScores
				+ "]";
	}
}
