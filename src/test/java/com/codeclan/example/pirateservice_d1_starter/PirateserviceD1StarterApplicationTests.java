package com.codeclan.example.pirateservice_d1_starter;

import com.codeclan.example.pirateservice_d1_starter.models.Pirate;
import com.codeclan.example.pirateservice_d1_starter.models.Raid;
import com.codeclan.example.pirateservice_d1_starter.models.Ship;
import com.codeclan.example.pirateservice_d1_starter.repositories.PirateRepository;
import com.codeclan.example.pirateservice_d1_starter.repositories.RaidRepository;
import com.codeclan.example.pirateservice_d1_starter.repositories.ShipRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class PirateserviceD1StarterApplicationTests {

	@Autowired
	PirateRepository pirateRepository;

	@Autowired
	ShipRepository shipRepository;

	@Autowired
	RaidRepository raidRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createPirateAndShip(){
		Ship ship = new Ship("The Floating Pineapple");
		shipRepository.save(ship);

		Pirate pirate1 = new Pirate("SpongeBob", "SquarePants", 32, ship);
		pirateRepository.save(pirate1);
	}

	@Test
	public void addPiratesAndRaids(){
		Ship ship = new Ship("The Flying Dutchman");
		shipRepository.save(ship);

		Pirate pirate1 = new Pirate("Jack", "Sparrow", 32, ship);
		pirateRepository.save(pirate1);

		Raid raid1 = new Raid("Tortuga", 100);
		raidRepository.save(raid1);

		raid1.addPirate(pirate1);
		raidRepository.save(raid1);
	}

	@Test
	public void canFindPiratesOver30(){
		List<Pirate> found = pirateRepository.findPiratesByAgeGreaterThan(30);
		assertTrue(found.size() > 0);
	}

	@Test
	public void canFindPiratesByFirstName(){
		List<Pirate> found = pirateRepository.findPiratesByFirstName("Jack");
		assertEquals("Jack", found.get(0).getFirstName());
	}

	@Test
	public void canFindPirateByLastName(){
		Pirate found = pirateRepository.findPirateByLastName("Sparrow");
		assertEquals("Sparrow", found.getLastName());
	}

	@Test
	public void canFindRaidByLocation(){
		List<Raid> found = raidRepository.findRaidByLocation("Tortuga");
		assertTrue(found.size() > 0);
	}

	@Test
	public void canFindPiratesByRaidsId(){
		List<Pirate> found = pirateRepository.findPiratesByRaidsId(1L);
		assertEquals(1, found.size());
		assertEquals("Jack", found.get(0).getFirstName());
	}

	@Test
	public void canFindShipsByPiratesFirstName(){
		List<Ship> found = shipRepository.findShipsByPiratesFirstName("Jack");
		assertTrue(found.size() > 0);
	}

	@Test
	public void canFindRaidsByShipsName(){
		Ship foundShip = shipRepository.getById(3L);
		List<Raid> found = raidRepository.findRaidsByPiratesShip(foundShip);
		assertEquals(2, found.size());
	}

}
