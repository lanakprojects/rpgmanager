package hh.rpgmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.rpgmanager.domain.Char;
import hh.rpgmanager.domain.CharRepository;
import hh.rpgmanager.domain.Game;
import hh.rpgmanager.domain.GameRepository;
import hh.rpgmanager.domain.User;
import hh.rpgmanager.domain.UserRepository;

@SpringBootApplication
public class RpgmanagerApplication {
	private static final Logger log = LoggerFactory.getLogger(RpgmanagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RpgmanagerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner clr(CharRepository charrepository, GameRepository gamerepository, UserRepository userrepository) {
		return (args) -> {
			log.info("insert example data");
			
			// Add example games
			gamerepository.save(new Game("Arkham Horror"));
			gamerepository.save(new Game("Dungeons and Dragons"));
			gamerepository.save(new Game("Blades in the Dark"));
			gamerepository.save(new Game("Warhammer"));
			gamerepository.save(new Game("Pathfinder"));
			gamerepository.save(new Game("Vampire: The Masquerade"));
			
			// Add example characters
			charrepository.save(new Char("Amber Syrah", "Whisper", 14, "Staff of the Woodlands", gamerepository.findByGameName("Blades in the Dark").get(0)));
			charrepository.save(new Char("Wandering Storm", "Ranger", 24, "Darklight Longbow", gamerepository.findByGameName("Dungeons and Dragons").get(0)));
			charrepository.save(new Char("Diana Stanley", "Cultist", 27, "Arcane Tome", gamerepository.findByGameName("Arkham Horror").get(0)));
			charrepository.save(new Char("Ashley Green", "Sorcerer", 4, "Weathered Cane", gamerepository.findByGameName("Dungeons and Dragons").get(0)));
			charrepository.save(new Char("Arthur Rei", "Druid", 20, "Chestnut Cane", gamerepository.findByGameName("Dungeons and Dragons").get(0)));
			charrepository.save(new Char("Luna Elric", "Paladin", 27, "Vintage Sword", gamerepository.findByGameName("Dungeons and Dragons").get(0)));
			charrepository.save(new Char("Draven Mist", "Barbarian", 14, "Thunderstorm Axe", gamerepository.findByGameName("Dungeons and Dragons").get(0)));
			charrepository.save(new Char("Zhep Sol", "Wizard", 28, "Silvergrace Rod", gamerepository.findByGameName("Dungeons and Dragons").get(0)));
			charrepository.save(new Char("Eve Delta", "Cleric", 27, "Mace of Disruption", gamerepository.findByGameName("Dungeons and Dragons").get(0)));
			charrepository.save(new Char("Claire Reimond", "Fighter", 3, "High Steel Blade", gamerepository.findByGameName("Dungeons and Dragons").get(0)));
			charrepository.save(new Char("Ashe Zain", "Cutter", 25, "Poisonous Knives", gamerepository.findByGameName("Blades in the Dark").get(0)));
			charrepository.save(new Char("Moon Nix", "Spider", 12, "Noble Codex", gamerepository.findByGameName("Blades in the Dark").get(0)));
			charrepository.save(new Char("Serena Azul", "Cutter", 18, "Titanium Claws", gamerepository.findByGameName("Blades in the Dark").get(0)));
			charrepository.save(new Char("Frey Selch", "Khaine", 9, "Inferno Katana", gamerepository.findByGameName("Warhammer").get(0)));
			charrepository.save(new Char("Scarlet Hardey", "Monk", 29, "Mighty Knuckles", gamerepository.findByGameName("Pathfinder").get(0)));
			charrepository.save(new Char("Emet Arche", "Archmage", 8, "Palladium Rod", gamerepository.findByGameName("Warhammer").get(0)));
			charrepository.save(new Char("Sami Vanguard", "Chosen", 13, "Mighty Battleaxe", gamerepository.findByGameName("Warhammer").get(0)));
			
			/* Add example user and admin hashing passwords with BCrypt 
			 user1
			 	username: Gamemaster
			 	password: gmPassword
			 user2
			 	username: Player
			 	password: playerPassword 
			*/
			User user1 = new User("Gamemaster", "$2y$10$kwZvo87m2BHlbA8UoDNCx.1d5xJVrajUEl6Z.asowgEGTGJJaa45G", "gm@rpg.fi", "ADMIN");
			User user2 = new User("Player", "$2y$10$BXnfXEGRId7B./OfW/4.jOV11msQaCkTVOihuVbx6663XqmAUWXXG", "player@rpg.fi", "USER");
			userrepository.save(user1);
			userrepository.save(user2);
			
			//Info log to check the repository
			log.info("fetch all characters");
			for (Char charecter : charrepository.findAll()) {
				log.info(charecter.toString());
			}

		};
	}
}
