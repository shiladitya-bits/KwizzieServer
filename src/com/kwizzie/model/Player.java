package com.kwizzie.model;

import java.util.Map;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.sun.xml.internal.txw2.annotation.XmlElement;

@Entity
public class Player {
	
	@Id
	private String userName;
	
	private String password;
	
	@Embedded
	private PlayerPersonalDetails details;

	private Map<String,Integer> quizRoomScores;
	
	public Player(){}
	
	public Player(String userName, String password,String photoURL,String name,String emailId) {
		details= new PlayerPersonalDetails(photoURL,name,emailId);
		this.userName = userName;
		this.password = password;
	}

	public Player(String userName, String password,String name,String emailId) {
		//TODO  default photoURL
		details= new PlayerPersonalDetails("",name,emailId);
		this.userName = userName;
		this.password = password;
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

	
	@Override
	public String toString() {
		return "Player [userName=" + userName + ", password=" + password
				+ ", details=" + details + ", quizRoomScores=" + quizRoomScores
				+ "]";
	}


	@XmlElement
	@Embeddable
	public static class PlayerPersonalDetails{
		private String photoUrl;
		private String name;
		@Id
		private String emailId;
		public String getPhotoUrl() {
			return photoUrl;
		}
		public PlayerPersonalDetails(){}
		
		public PlayerPersonalDetails(String photoUrl, String name,
				String emailId) {
			this.photoUrl = photoUrl;
			this.setName(name);
			this.emailId = emailId;
		}
		public void setPhotoUrl(String photoUrl) {
			this.photoUrl = photoUrl;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "PlayerPersonalDetails [photoUrl=" + photoUrl + ", name="
					+ name + ", emailId=" + emailId + "]";
		}
	
		
	}
	
	
}
