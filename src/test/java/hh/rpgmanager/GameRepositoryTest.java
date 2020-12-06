package hh.rpgmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.rpgmanager.domain.Game;
import hh.rpgmanager.domain.GameRepository;

@DataJpaTest
public class GameRepositoryTest {
	
	 @Autowired
	    private GameRepository grepository;
	 
	 @Test
	    public void findByGameNameShouldReturnGame() {
	        List<Game> games = grepository.findByGameName("Pathfinder");
	        assertTrue(games.size() == 1);
	        assertEquals("Pathfinder", games.get(0).getGameName());
	 }
}