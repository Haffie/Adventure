package uk.co.amberservices.AdventureServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

import uk.co.amberservices.AdventureModel.Item;
import uk.co.amberservices.AdventureModel.Location;
import uk.co.amberservices.AdventureModel.Player;
import uk.co.amberservices.AdventurePersistence.AdventureDAO;

public class Game {

	public final String NORTH = "NORTH";
	public final String SOUTH = "SOUTH";
	public final String EAST = "EAST";
	public final String WEST = "WEST";
	public final String VERB_TAKE = "TAKE";
	public final String VERB_DROP = "DROP";
	public final String INVENTORY = "INVENTORY";
	public final String VERB_EXAMINE  = "EXAMINE";
	public final String VERB_LOOK  = "LOOK";

	private Integer id;
	private Map<Integer, Location> locations;
	private Player player;

	public Integer getId() {
		return id;
	}

	public Map<Integer, Location> getLocations() {
		return locations;
	}

	public Game(Player player, String adventureName) {
		super();

		// TODO What am I going to do with the adventure name?
		this.player = player;
		AdventureDAO adventureDao = new AdventureDAO(adventureName);
		this.locations = adventureDao.getLocations();
		// this.items = new AdventureDAO(adventureName).getItems();
	}

	public static void main(String[] args) {
		Player player = new Player("Adrian");
		
		Game game = new Game(
				player,
				"//Users//adrianbell//Documents//Personal//Adventure//AdventurePersistence//data//adventure1.json");
		game.play();
	}

	public void play() {
		Location location = this.locations.get(1);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text;
		
		showLocation(location);
		while (true != false) {
			System.out.println("What now?");
			try {
				text = br.readLine().toUpperCase();
				StringTokenizer st = new StringTokenizer(text, " ");
				String verb = null;
				String noun = null;
				
				if (st.hasMoreElements()) 
				{
					verb = st.nextToken();
				}
				if (st.hasMoreElements()) 
				{
					noun = st.nextToken();
				}
				
				if (verb == null || (!verb.equals(VERB_LOOK) && !verb.equals(VERB_EXAMINE) && !verb.equals(INVENTORY) && !verb.equals(VERB_DROP) && !verb.equals(VERB_TAKE) && !verb.equals(NORTH)
						&& !verb.equals(SOUTH) && !verb.equals(EAST)
						&& !verb.equals(WEST))) {
					System.out.println("Eh?  I don't understand!");
				} else {
					if (verb.equals(VERB_LOOK)) 
					{
						this.showLocation(location);
					}	
					else if (verb.equals(VERB_EXAMINE)) 
					{
						if (player.getInventory().size() == 0 && location.getItems().size() == 0)
						{
							System.out.println("There is no " + noun.toLowerCase());
						}
						else 
						{
							System.out.println("You examine the " + noun.toLowerCase());
							if (player.getInventory().containsKey(noun.toUpperCase()))
							{
								System.out.println(player.getInventory().get(noun.toUpperCase()).getDetails());
							}
							else if (location.getItems().containsKey(noun.toUpperCase().toUpperCase()))
							{
								System.out.println(location.getItems().get(noun.toUpperCase()).getDetails());
							}
						}
					}	
					else if (verb.equals(VERB_TAKE)) 
					{
						if (noun != null)
						{
							Item item = location.getItems().get(noun);
							if (item == null)
							{
								System.out.println("I cannot see a " + noun.toLowerCase());
							}
							else
							{
								player.getInventory().put(item.getName().toUpperCase(), item);
								location.getItems().remove(item.getName().toUpperCase());
								System.out.println("You pick up the " + noun.toLowerCase());
							}
						}
					} 
					else if (verb.equals(VERB_DROP)) {
						if (noun != null)
						{
							Item item = player.getInventory().get(noun);
							if (item == null)
							{
								System.out.println("You don't have a " + noun.toLowerCase());
							}
							else
							{
								location.getItems().put(item.getName().toUpperCase(), item);
								player.getInventory().remove(item.getName().toUpperCase());
								System.out.println("You drop the " + noun.toLowerCase());
							}
						}
					} 
					else if (verb.equals(INVENTORY)) 
					{
						if (player.getInventory().size() > 0) 
						{
							System.out.println("You are carrying:");
							for (Item item : player.getInventory().values()) {
								System.out.println(item.getDescription());
							}
						} 
						else
						{
							System.out.println("You are not carrying anything.");
							
						}
					} 
					else 
					{
						if (location.getNeighbours().containsKey(text)) {
							location = location.getNeighbours().get(text);
							showLocation(location);
						} else {
							System.out.println("You cannot go that way.");
						}
					}
					System.out.println("");
				}

			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private void showLocation(Location location)
	{
		System.out.println("");
		System.out.println(location.getDescription());
		System.out.print("You can see exits to the ");

		for (Map.Entry<String, Location> entry : location.getNeighbours()
				.entrySet()) {
			System.out.print(entry.getKey().toLowerCase() + " ");
		}

		if (location.getItems().size() > 0) {
			System.out.println("\nYou can also see");

			for (Map.Entry<String, Item> itemEntry : location.getItems()
					.entrySet()) {
				System.out.println(itemEntry.getValue().getDescription());
			}
		}
	}
}
