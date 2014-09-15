package uk.co.amberservices.AdventurePersistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.amberservices.AdventureModel.Item;
import uk.co.amberservices.AdventureModel.Location;

public class LocationDAO extends BaseDAO {
	
	final static String LOCATIONS = "locations";
    final static String ID = "id";
    final static String DESCRIPTION = "description";
    final static String NEIGHBOURS = "neighbours";
    final static String DIRECTION = "direction";
    
	private HashMap<Integer, Location> locations;
	
    @SuppressWarnings("unchecked")
	public HashMap<Integer, Location> getLocations()
    {

	if (locations == null)
	{
	    String filename = "//Users//adrianbell//Documents//Personal//Adventure//AdventurePersistence//data//adventure1.json";
	    locations = new HashMap<Integer, Location>();
	    List<Item> items = null;
	    
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
	    		location = new Location(id, description, null);
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
			    		neighbour = new Location(neighbourId, null, null);
			    		locations.put(neighbourId, neighbour);
			    	}
			    	location.getNeighbours().put(direction, neighbour);
			    }
			}
	    	locations.put(id, location);
	    }
		}
	return locations;
    }
	
}
