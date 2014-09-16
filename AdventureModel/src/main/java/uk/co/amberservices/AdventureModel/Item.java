package uk.co.amberservices.AdventureModel;

public class Item {

	private Integer id;
	private String name;
	private String description;
	private String details;
	private boolean pickup;
	
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
	public Item(Integer id, String name, String description, String details, boolean pickup) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.details = details;
		this.pickup = pickup;
	}
	public boolean isPickup() {
		return pickup;
	}
	public void setPickup(boolean pickup) {
		this.pickup = pickup;
	}
	
}