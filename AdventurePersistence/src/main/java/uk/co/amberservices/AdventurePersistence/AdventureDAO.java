package uk.co.amberservices.AdventurePersistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.amberservices.AdventureModel.Item;
import uk.co.amberservices.AdventureModel.Location;

public class AdventureDAO extends BaseDAO {
	
	private String filename = null;
	
	final static String LOCATIONS = "locations";
	final static String DETAILS = "details";
	final static String NAME = "name";
	final static String ITEMS = "items";
    final static String ID = "id";
    final static String DESCRIPTION = "description";
    final static String NEIGHBOURS = "neighbours";
    final static String DIRECTION = "direction";
    
	private HashMap<Integer, Location> locations;
	private HashMap<Integer, Item> items;
	
    public AdventureDAO(String filename) {
		super();
		this.filename = filename;
		this.items = getItems();
	}
    
    @SuppressWarnings("unchecked")
	private HashMap<Integer, Item> getItems() {

		if (items == null) {
			items = new HashMap<Integer, Item>();

			HashMap<String, Object> messageMap = this.readFile(filename);

			for (Map<String, Object> itemObject : (List<Map<String, Object>>) messageMap
					.get(ITEMS)) {
				Item item = null;

				final Integer id = Integer.valueOf((String) itemObject.get(ID));
				final String name = (String)itemObject.get(NAME);
				final String description = (String) itemObject.get(DESCRIPTION);
				final String details = (String)itemObject.get(DETAILS);

				item = new Item(id, name, description, details);
				items.put(id, item);
			}
		}
		return items;
	}

	@SuppressWarnings("unchecked")
	public HashMap<Integer, Location> getLocations()
    {

	if (locations == null)
	{
	    locations = new HashMap<Integer, Location>();
	    HashMap<String, Object> messageMap = this.readFile(filename);

	    for (Map<String, Object> locationObject : (List<Map<String, Object>>) messageMap
		    .get(LOCATIONS))
	    {
	    	Location location = null;
	    	
	    	final Integer id = Integer.valueOf((String) locationObject
	    			.get(ID));
	    	final String description = (String)locationObject.get(DESCRIPTION);
	    	
	    	location = locations.get(id);
	    	if (location == null)
	    	{
	    		location = new Location(id, description);
	    	}
	    	else
	    	{
	    		location.setDescription(description);
	    	}
	    	
	    	if (locationObject.containsKey(NEIGHBOURS))
			{
			    List<Map<String, String>> neighboursMap = (List<Map<String, String>>) locationObject
				    .get(NEIGHBOURS);
			    for (Map<String, String> neighbourMap : neighboursMap)
			    {
			    	String direction = (String) neighbourMap
			    			.get(DIRECTION);
			    	Integer neighbourId = Integer.valueOf((String)neighbourMap.get(ID));
			    	Location neighbour = locations.get(neighbourId);
			    	if (neighbour == null)
			    	{
			    		neighbour = new Location(neighbourId, null);
			    		locations.put(neighbourId, neighbour);
			    	}
			    	location.getNeighbours().put(direction, neighbour);
			    }
			}
	    	
	    	if (locationObject.containsKey(ITEMS))
	    	{
	    		List<String> itemIds = (List<String>)locationObject.get(ITEMS);
	    		for (String itemId : itemIds)
	    		{
	    			Item item = this.items.get(Integer.valueOf(itemId));
	    			location.getItems().put(item.getName().toUpperCase(), item);
	    		}
	    	}
	    	locations.put(id, location);
	    }
		}
	return locations;
    }
	
}
