package uk.co.amberservices.AdventureModel;

import java.util.HashMap;
import java.util.Map;

public class Location {
	
	private Integer id;
	private String description;
	private Map<String, Item> items = new HashMap<String, Item>();
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
	public Map<String, Item> getItems() {
		return items;
	}
	public Map<String, Location> getNeighbours() {
		return neighbours;
	}
	
	public Location(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
