package uk.co.amberservices.AdventureModel;

public class Item {

	private Integer id;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public Item(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
}