package com.kwizzie.restservices;

import java.net.UnknownHostException;

import com.google.code.morphia.Morphia;
import com.kwizzie.dao.PlayerDAO;
import com.kwizzie.model.Player;
import com.mongodb.Mongo;

public class InitLoader {
	public InitLoader(){
		System.out.println("init loader called");
	}
	
	public static void main(String[] args) throws UnknownHostException{
		Mongo mongo = new Mongo("localhost");
		Morphia morphia = new Morphia();
		PlayerDAO pd = new PlayerDAO(mongo, morphia, "test");
		pd.save(new Player("neha2","waheguru","","neha madaan","@nmmadaan"));
		System.out.println(pd.findAll().toString());
	}
}
