package travel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import travel.beans.Vacation;
import travel.repository.VacationRepository;

@RequestMapping("vacations")
@Controller
public class VacationController {
	@Autowired
	VacationRepository repo;


	/**
	 * Displays all vacations
	 * @param model
	 * @return myTrips
	 */
	@GetMapping("")
	public String viewAllVacations(Model model) {
		model.addAttribute("vacation", repo.findAll());
		return "myTrips";
	}
	
	/**
	 * Displays a vacation based on its id to edit
	 * @param id
	 * @param model
	 * @return planYourTrip
	 */
	@GetMapping("/{id}")
	public String viewVacation(@PathVariable("id") int id, Model model) {
		Vacation vacation = repo.findById(id).orElse(null);
		model.addAttribute("newVacation", vacation);
		return ("planYourTrip");
	}
	
	/**
	 * Save a vacation
	 * @param vacation
	 * @param model
	 * @return myTrips
	 */
	@PostMapping("/{id}")
	public String saveVacation(@Valid @ModelAttribute("newVacation") Vacation vacation, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("newVacation", vacation);
			return "planYourTrip";
		}
		repo.save(vacation);
		return "redirect:/vacations";
	}

	/**
	 * Delete a vacation
	 * @param vacation
	 * @param model
	 * @return myTrips
	 */
	@GetMapping("/delete/{id}")
	public String deleteVacation(@PathVariable("id") int id) {
		Vacation vacation = repo.findById(id).orElse(null);
		repo.delete(vacation);
		return "redirect:/vacations";
	}
	
	/**
	 * Add a new vacations
	 * @param model
	 * @return planYourTrip
	 */
	@GetMapping("/new")
	public String newVacation(Model model) {
		model.addAttribute("newVacation", new Vacation());
		return ("planYourTrip");
	}
}
