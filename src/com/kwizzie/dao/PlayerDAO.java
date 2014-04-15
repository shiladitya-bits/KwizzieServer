package com.kwizzie.dao;

import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.kwizzie.model.Player;
import com.mongodb.Mongo;

public class PlayerDAO extends BasicDAO<Player,String> {

	public PlayerDAO(Mongo mongo , Morphia morphia,String dbName) {
		super(mongo,morphia, dbName);
	}

	public List<Player> findAll(){
		return ds.find(Player.class).asList();
	}
	
	public Player getPlayer(String username){
		Query<Player> query= ds.createQuery(Player.class).field("username").equal(username);
		List<Player> results = query.asList();
		if(results.isEmpty()){
			return null;
		}
		return results.get(0);
	}

}
