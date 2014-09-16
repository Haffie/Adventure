package uk.co.amberservices.AdventurePersistence;


import static org.junit.Assert.assertEquals;
import java.util.HashMap;

import org.junit.Test;

import uk.co.amberservices.AdventureModel.Location;

public class TestLocationDAO {
	
	@Test
	public void shouldReturnLocations() {
		AdventureDAO dao = new AdventureDAO("//Users//adrianbell//Documents//Personal//Adventure//AdventurePersistence//data//adventure1.json");
		HashMap<Integer, Location> locations = dao.getLocations();
		assertEquals(10, locations.size());
	}
}
