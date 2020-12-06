package hh.rpgmanager.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.rpgmanager.domain.Char;
import hh.rpgmanager.domain.CharRepository;
import hh.rpgmanager.domain.GameRepository;

@Controller
public class CharController {
	
	@Autowired
	private CharRepository charrepository;
	@Autowired
	private GameRepository gamerepository;

	// Display all characters
	@GetMapping({ "/", "/charlist" })
	public String charList(Model model) {
		model.addAttribute("chars", charrepository.findAll());
		return "charlist";
	}
	
	// RESTful service to get all characters
    @RequestMapping(value="/chars", method = RequestMethod.GET)
    public @ResponseBody List<Char> charListRest() {	
        return (List<Char>) charrepository.findAll();
    }    

	// RESTful service to get a character by id
    @RequestMapping(value="/char/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Char> findCharRest(@PathVariable("id") Long charId) {	
    	return charrepository.findById(charId);
    }

	// Add a new character
	@RequestMapping(value = "/add")
	public String addChar(Model model) {
		model.addAttribute("character", new Char());
		model.addAttribute("games", gamerepository.findAll());
		return "addchar";
	}

	// Save a character
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Char character) {
		charrepository.save(character);
		return "redirect:charlist";
	}

	// Delete a character (requires admin authorization)
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteChar(@PathVariable("id") Long charId, Model model) {
		charrepository.deleteById(charId);
		return "redirect:../charlist";
	}
	
	// Edit a character (requires admin authorization)
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editChar(@PathVariable("id") Long charId, Model model) {
		Optional<Char> character = charrepository.findById(charId);
		model.addAttribute("character", character);
		model.addAttribute("games", gamerepository.findAll());
		return "editchar";
	}
	
	// Login
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	// Sort all characters on list by name
	@RequestMapping(value="/charsbyname", method = RequestMethod.GET)
	public String charListByName(Model model) {
				model.addAttribute("chars", charrepository.findByOrderByCharName());
				return "charlist";
			}
	
	// Sort all characters on list by class
	@RequestMapping(value="/charsbyclass", method = RequestMethod.GET)
	public String charListByClass(Model model) {
				model.addAttribute("chars", charrepository.findByOrderByCharClass());
				return "charlist";
			}
	
	// Sort all characters on list by level
	@RequestMapping(value="/charsbylevel", method = RequestMethod.GET)
	public String charListByLevel(Model model) {
				model.addAttribute("chars", charrepository.findByOrderByCharLevel());
				return "charlist";
			}
	
	// Sort all characters on list by weapon
	@RequestMapping(value="/charsbyweapon", method = RequestMethod.GET)
	public String charListByWeapon(Model model) {
				model.addAttribute("chars", charrepository.findByOrderByCharWeapon());
				return "charlist";
			}
	
	// Sort all characters on list by game title
	@RequestMapping(value="/charsbygame", method = RequestMethod.GET)
	public String charListByGame(Model model) {
				model.addAttribute("chars", charrepository.findByOrderByGame());
				return "charlist";
			}

}