package uk.co.amberservices.AdventureServices.Util;

import java.util.Map;
import java.util.Set;

import uk.co.amberservices.AdventureModel.Item;
import uk.co.amberservices.AdventureModel.Location;
import uk.co.amberservices.AdventureModel.Player;

public class GameUtil {
	
	private Player player;
	private Location location;
	
	public GameUtil(Player player, Location location) {
		super();
		this.player = player;
		this.location = location;
	}
	
	public void showHelp(Set<String> verbs)
	{
		System.out.println("You can use the following verbs: ");
		for (String verb : verbs) 
		{
			System.out.print(verb + " ");
		}
	}

	public void showLocation()
	{
		System.out.println("");
		System.out.println(location.getDescription());
		System.out.print("You can see exits to the ");

		for (Map.Entry<String, Location> entry : location.getNeighbours()
				.entrySet()) {
			System.out.print(entry.getKey().toLowerCase() + " ");
		}

		if (location.getItems().size() > 0) {
			System.out.println("\n\nYou can also see");

			for (Map.Entry<String, Item> itemEntry : location.getItems()
					.entrySet()) {
				System.out.println(itemEntry.getValue().getDescription());
			}
		}
		System.out.println();
	}
	
	public void examineItem(String noun)
	{

	if (!player.getInventory().containsKey(noun.toUpperCase()) && !location.getItems().containsKey(noun.toUpperCase()))
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

	public void takeItem(String noun)
	{
		if (noun != null)
		{
			Item item = location.getItems().get(noun);
			if (item == null)
			{
				System.out.println("I cannot see a " + noun.toLowerCase());
			}
			else if (!item.isPickup())
			{
				System.out.println("You cannot pick up the " + noun.toLowerCase());
			}
			else
			{
				player.getInventory().put(item.getName().toUpperCase(), item);
				location.getItems().remove(item.getName().toUpperCase());
				System.out.println("You pick up the " + noun.toLowerCase());
			}
		}
	}
	
	public void dropItem(String noun)
	{
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
	
	public void listInventory() 
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
	
	public Location move(String direction)
	{

		if (location.getNeighbours().containsKey(direction)) {
			location = location.getNeighbours().get(direction);
			showLocation();
		} else {
			System.out.println("You cannot go that way.");
		}
		return location;
	}
}