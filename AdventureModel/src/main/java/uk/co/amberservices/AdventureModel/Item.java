package uk.co.amberservices.AdventureModel;

import java.util.HashSet;
import java.util.Set;

public class Item {

	private Integer id;
	private String name;
	private String description;
	private String details;
	private boolean pickup;
	private Set<String> associatedVerbs = new HashSet<String>();
	
	
	//private Location location
	//private Player player;
	
	public Integer getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details)
	{
		this.details = details;
	}
	public String getName() {
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Item(Integer id) {
		super();
		this.id = id;
	}
	public boolean isPickup() {
		return pickup;
	}
	public void setPickup(boolean pickup) {
		this.pickup = pickup;
	}
	public Set<String> getAssociatedVerbs() {
		return associatedVerbs;
	}
	public Item(Integer id, String name, String description, String details, boolean pickup) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.details = details;
		this.pickup = pickup;
	}
}