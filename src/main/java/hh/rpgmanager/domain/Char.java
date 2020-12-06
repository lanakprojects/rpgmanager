package hh.rpgmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Char {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long charId;
	private String charName;
	private String charClass;
	private int charLevel;
	private String charWeapon;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "gameId")
	private Game game;

	public Char() {
	}

	public Char(String charName, String charClass, int charLevel, String charWeapon, Game game) {
		super();
		this.charName = charName;
		this.charClass = charClass;
		this.charLevel = charLevel;
		this.charWeapon = charWeapon;
		this.game = game;
	}

	public Long getCharId() {
		return charId;
	}

	public void setCharId(Long charId) {
		this.charId = charId;
	}

	public String getCharName() {
		return charName;
	}

	public void setCharName(String charName) {
		this.charName = charName;
	}

	public String getCharClass() {
		return charClass;
	}

	public void setCharClass(String charClass) {
		this.charClass = charClass;
	}

	public int getCharLevel() {
		return charLevel;
	}

	public void setCharLevel(int charLevel) {
		this.charLevel = charLevel;
	}

	public String getCharWeapon() {
		return charWeapon;
	}

	public void setCharWeapon(String charWeapon) {
		this.charWeapon = charWeapon;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public String toString() {
		if (this.game != null)
			return "Char [charId=" + charId + ", charName=" + charName + ", charClass=" + charClass + ", charLevel="
					+ charLevel + ", charWeapon=" + charWeapon + ", game=" + this.getGame() + "]";
		else
			return "Char [charId=" + charId + ", charName=" + charName + ", charClass=" + charClass + ", charLevel="
			+ charLevel + ", charWeapon=" + charWeapon + "]";
	}
	
}
