package hh.rpgmanager.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.rpgmanager.domain.NewUser;
import hh.rpgmanager.domain.User;
import hh.rpgmanager.domain.UserRepository;

@Controller
public class UsersController {
	
	@Autowired
	private UserRepository userrepository;

	@RequestMapping(value="/signup")
	public String signUp(Model model){
		model.addAttribute("newuser", new NewUser());
		return "signup";
	}
	
	@RequestMapping(value="saveuser", method=RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("newuser") NewUser newUser, BindingResult bindingResult) {
    	
		// validation 
		if (!bindingResult.hasErrors()) { 
    		
			// check that password entries are matching
			if (newUser.getPassword().equals(newUser.getPasswordCheck())) { 		
	    		String newPassword = newUser.getPassword();
		    	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		    	String hashedPassword = bcrypt.encode(newPassword);
	
		    	User newPlayer = new User();
		    	newPlayer.setUsername(newUser.getUsername());
		    	newPlayer.setPasswordHash(hashedPassword);
		    	newPlayer.setEmail(newUser.getEmail());
		    	newPlayer.setRole("USER");
		    	// check if user already exists
		    	if (userrepository.findByUsername(newUser.getUsername()) == null) {
		    		userrepository.save(newPlayer);
		    	} else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
			} else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }
		
		// RESTful service to get all users
	    @RequestMapping(value="/users", method = RequestMethod.GET)
	    public @ResponseBody List<User> charListRest() {	
	        return (List<User>) userrepository.findAll();
	    } 
	
}