package uk.co.amberservices.AdventureServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

import uk.co.amberservices.AdventureModel.Location;
import uk.co.amberservices.AdventureModel.Player;
import uk.co.amberservices.AdventurePersistence.LocationDAO;

public class Game {
	
	public final String NORTH = "NORTH";
	public final String SOUTH = "SOUTH";
	public final String EAST = "EAST";
	public final String WEST = "WEST";
	
	private Integer id;
	private Map<Integer, Location> locations;
	private Player player;
	
	private LocationDAO dao = new LocationDAO();
	
	public Integer getId() {
		return id;
	}
	public Map<Integer, Location> getLocations() {
		return locations;
	}
	
	public Game(Player player, String adventureName) {
		super();
		
		//TODO What am I going to do with the adventure name?	
		this.player = player;
	}
	
	
	public static void main (String[] args)
	{
		Game game = new Game(null, "Blah blah");
		game.play();
	}
	
	public void play() {
		this.locations = dao.getLocations();
		
		Location location = this.locations.get(1);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text;
        while (true != false) 
        {
        	System.out.println("");
        	System.out.println(location.getDescription());
        	System.out.print("You can see exits to the ");
        	
        	for (Map.Entry<String, Location> entry : location.getNeighbours().entrySet())
        	{
        	    System.out.print(entry.getKey().toLowerCase() + " ");
        	}
        	
        	System.out.println("");
        	System.out.println("What now?");
        	try{
        		text = br.readLine().toUpperCase();
        		System.out.println(text);
        		
        		if (!text.equals(NORTH) && !text.equals(SOUTH) && !text.equals(EAST) && !text.equals(WEST) ) {
        			System.out.println("Eh?  I don't understand!");
        		}
        		else
        		{
        			if (location.getNeighbours().containsKey(text))
        			{
        				location = location.getNeighbours().get(text);
        			}
        			else
        			{
        				System.out.println("You cannot go that way.");
        			}
        		}
        		
        	}catch(NumberFormatException nfe){
        		System.err.println("Invalid Format!");
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        }
		
	}
	
	
	
	
	
	
	
	
	
	
}
