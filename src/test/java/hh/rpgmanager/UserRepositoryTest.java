package hh.rpgmanager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.rpgmanager.domain.User;
import hh.rpgmanager.domain.UserRepository;

@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
    private UserRepository repository;
 
	 @Test
	    public void findByUsernameShouldReturnUser() {
	       User user = repository.findByUsername("Gamemaster");
	       assertNotNull(user.getId());
	    }

}
