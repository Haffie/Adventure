package uk.co.amberservices.AdventureServices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
	
	private Location location;
	private Player player;
	private Set<String> verbSet;
	
	private String[] verbs = {VERB_HELP, NORTH, SOUTH, EAST, WEST, VERB_LOOK, VERB_EXAMINE, VERB_INVENTORY, VERB_DROP, VERB_TAKE};
	
	public Parser(Location location, Player player) {
		super();
		this.location = location;
		this.player = player;
		this.verbSet = new HashSet<String>(Arrays.asList(verbs));
	}

	public Location parse(String verb, String noun)
	{
		GameUtil gameUtil = new GameUtil(player, location);
		
		//if (verb == null || (!verb.equals(VERB_LOOK) && !verb.equals(VERB_EXAMINE) && !verb.equals(VERB_INVENTORY) && !verb.equals(VERB_DROP) && !verb.equals(VERB_TAKE) && !verb.equals(NORTH)
		//		&& !verb.equals(SOUTH) && !verb.equals(EAST)
		//		&& !verb.equals(WEST))) {
		if (verb == null || !verbSet.contains(verb))
		{
			System.out.println("Eh?  I don't understand!");
		} 
		else {
			
			if (verb.equals(VERB_LOOK)) 
			{
				gameUtil.showLocation();
			}
			else if (verb.equals(VERB_HELP))
			{
				gameUtil.showHelp(verbSet);
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
