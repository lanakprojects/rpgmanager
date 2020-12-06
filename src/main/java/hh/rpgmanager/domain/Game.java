package hh.rpgmanager.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Game {
	 @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private Long gameId;
		private String gameName;
		
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
		private List<Char> chars;
		
		public Game() {}
		
		public Game(String gameName) {
			super();
			this.gameName = gameName;
		}

		public Long getGameId() {
			return gameId;
		}

		public void setGameId(Long gameId) {
			this.gameId = gameId;
		}

		public String getGameName() {
			return gameName;
		}

		public void setGameName(String gameName) {
			this.gameName = gameName;
		}

		public List<Char> getChars() {
			return chars;
		}

		public void setChars(List<Char> chars) {
			this.chars = chars;
		}

		@Override
		public String toString() {
			return "Game [gameId=" + gameId + ", gameName=" + gameName + "]";
		}
		
}