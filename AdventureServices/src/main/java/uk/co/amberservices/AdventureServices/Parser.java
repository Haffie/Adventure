package uk.co.amberservices.AdventureServices;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import uk.co.amberservices.AdventureModel.Item;
import uk.co.amberservices.AdventureModel.Location;
import uk.co.amberservices.AdventureModel.Player;
import uk.co.amberservices.AdventureServices.Util.GameUtil;

public class Parser {
	
	public static final String NORTH = "NORTH";
	public static final String SOUTH = "SOUTH";
	public static final String EAST = "EAST";
	public static final String WEST = "WEST";
	public static final String VERB_TAKE = "TAKE";
	public static final String VERB_DROP = "DROP";
	public static final String VERB_INVENTORY = "INVENTORY";
	public static final String VERB_EXAMINE  = "EXAMINE";
	public static final String VERB_LOOK  = "LOOK";
	public static final String VERB_HELP = "HELP";
	
	//TODO This is wrong because I am giving the parser state.  Surely it has none!
	private Location location;
	private Player player;
	private Set<String> regularVerbs;
	private Map<String, Map<String, Item>> verbItems;
	
	private String[] verbs = {VERB_HELP, NORTH, SOUTH, EAST, WEST, VERB_LOOK, VERB_EXAMINE, VERB_INVENTORY, VERB_DROP, VERB_TAKE};
	
	public Parser(Location location, Player player, Map<Integer, Item> allItems) {
		super();
		this.location = location;
		this.player = player;
		this.regularVerbs = new HashSet<String>(Arrays.asList(verbs));
		this.verbItems = new HashMap<String, Map<String, Item>>();
		for (Map.Entry<Integer, Item> itemEntry : allItems.entrySet())
		{
			Item item = itemEntry.getValue();
			if (item.getAssociatedVerbs().size() > 0) 
			{
				for (String verb : item.getAssociatedVerbs())
				{
					Map<String, Item> items;
					if (verbItems.containsKey(verb))
					{
						items = verbItems.get(verb);
					}
					else
					{
						items = new HashMap<String, Item>();
						verbItems.put(verb.toUpperCase(), items);
					}
					items.put(item.getName().toUpperCase(), item);
				}
			}
		}
	}

	public Location parse(String verb, String noun)
	{
		GameUtil gameUtil = new GameUtil(player, location);
		
		if (verb == null || (!regularVerbs.contains(verb) && !verbItems.containsKey(verb)))
		{
			System.out.println("Eh?  I don't understand!");
		} 
		else if (verbItems.containsKey(verb) && noun == null)
		{
			System.out.println(verb.toLowerCase() + " what?");
		}
		else if (verbItems.containsKey(verb))
		{
			Map<String, Item> items = verbItems.get(verb);
			Item item = items.get(noun.toUpperCase());
			if (item == null || (!location.getItems().containsKey(noun.toUpperCase()) && !player.getInventory().containsKey(noun.toUpperCase())))
			{
				System.out.println("There is not a " + noun.toLowerCase() + " here.");
			}
			else
			{
				System.out.println("You " + verb.toLowerCase() + "ed the " + noun.toLowerCase());
				System.out.println("And now I need to work out the consequences of this action!");
			}
		}
		else
		{	
			if (verb.equals(VERB_LOOK)) 
			{
				gameUtil.showLocation();
			}
			else if (verb.equals(VERB_HELP))
			{
				gameUtil.showHelp(regularVerbs, verbItems);
			}
			else if (verb.equals(VERB_EXAMINE)) 
			{
				gameUtil.examineItem(noun);
			}	
			else if (verb.equals(VERB_TAKE)) 
			{
				gameUtil.takeItem(noun);
			} 
			else if (verb.equals(VERB_DROP)) {
				gameUtil.dropItem(noun);
			} 
			else if (verb.equals(VERB_INVENTORY)) 
			{
				gameUtil.listInventory();
			} 
			else 
			{
				location = gameUtil.move(verb);
			}
			System.out.println("");
		}	
		return location;
	}
}
