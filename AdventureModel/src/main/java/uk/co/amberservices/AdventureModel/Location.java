package uk.co.amberservices.AdventureModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {
	
	private Integer id;
	private String description;
	private List<Item> items;
	private Map<String, Location> neighbours = new HashMap<String, Location>();
	
	public Integer getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Item> getItems() {
		return items;
	}
	public Map<String, Location> getNeighbours() {
		return neighbours;
	}
	
	public Location(Integer id, String description, List<Item> items) {
		super();
		this.id = id;
		this.description = description;
		this.items = items;
	}
}
