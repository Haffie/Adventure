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
import uk.co.amberservices.AdventureServices.Util.GameUtil;

public class Game {

	private Integer id;
	private Map<Integer, Location> locations;
	private Map<Integer, Item> allItems;
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
		this.allItems = adventureDao.getItems();
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
		Parser parser = new Parser(location, player, allItems);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text;
		
		new GameUtil(player, location).showLocation();
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
				
				location = parser.parse(verb, noun);

			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
