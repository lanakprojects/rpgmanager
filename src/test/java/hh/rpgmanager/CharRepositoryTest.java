package hh.rpgmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.rpgmanager.domain.Char;
import hh.rpgmanager.domain.CharRepository;
import hh.rpgmanager.domain.Game;

@DataJpaTest
public class CharRepositoryTest {
	
	 @Autowired
	    private CharRepository charrepository;
	 
	 @Test
	    public void findByCharNameShouldReturnChar() {
	        List<Char> chars = charrepository.findByCharName("Amber Syrah");
	        assertTrue(chars.size() == 1);
	        assertEquals("Amber Syrah", chars.get(0).getCharName());
	    }
	 
	 @Test
	    public void creatingNewChar() {
	    	Char character = new Char("Ari Fortuna", "Rogue", 1, "Iron Daggers", new Game ("Star Trek Adventures"));	
	    	charrepository.save(character);
	    	assertNotNull(character.getCharId());
	    }
	 
	 @Test
		 public void deletingChar() {
		 	List<Char> chars = charrepository.findByCharName("Luna Elric");
		 	long delId = chars.get(0).getCharId();
	    	charrepository.deleteById(delId);
	    	assertEquals(Optional.empty(), charrepository.findById(delId));
		 }

}
