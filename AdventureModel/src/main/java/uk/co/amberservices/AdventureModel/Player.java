package uk.co.amberservices.AdventureModel;

import java.util.List;

public class Player {
	
	private String name;
	private List<Item> inventory;
	
	public String getName() {
		return name;
	}
	public List<Item> getInventory() {
		return inventory;
	}
	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}
	
	public Player(String name, List<Item> inventory) {
		super();
		this.name = name;
		this.inventory = inventory;
	}

}
