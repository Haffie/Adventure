package uk.co.amberservices.AdventureModel;

import java.util.HashMap;
import java.util.Map;

public class Player {
	
	private String name;
	private Map<String, Item> inventory = null;
	
	public String getName() {
		return name;
	}
	public Map<String, Item> getInventory() {
		return inventory;
	}
	
	public Player(String name) {
		super();
		this.name = name;
		this.inventory = new HashMap<String, Item>();
	}

}
