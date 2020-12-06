package hh.rpgmanager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CharRepository extends CrudRepository<Char, Long>{
	List<Char> findByCharName(String charName);
	List<Char> findByCharNameIgnoreCase(String charName);
	List<Char> findByCharClassIgnoreCase(String charClass);
	
	//Sorting options
	List<Char> findByOrderByCharName();
	List<Char> findByOrderByCharClass();
	List<Char> findByOrderByCharLevel();
	List<Char> findByOrderByCharWeapon();
	List<Char> findByOrderByGame();
}
