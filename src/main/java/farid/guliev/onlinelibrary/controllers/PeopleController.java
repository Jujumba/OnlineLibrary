package farid.guliev.onlinelibrary.controllers;

import farid.guliev.onlinelibrary.models.Person;
import farid.guliev.onlinelibrary.services.PeopleService;
import farid.guliev.onlinelibrary.util.PeopleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final PeopleValidator peopleValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, PeopleValidator peopleValidator) {
        this.peopleService = peopleService;
        this.peopleValidator = peopleValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("books",peopleService.getBooksById(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        peopleValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors()){
            return "people/new";
        }else {
            peopleService.save(person);
            return "redirect:/people";
        }
    }

    @GetMapping("/{id}/edit")
    public String editPeople(Model model, @PathVariable("id") int id) {
        model.addAttribute("person",peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        peopleValidator.validate(person,bindingResult);

        if(bindingResult.hasErrors())
            return "people/edit";
        else {
            person.setId(id);
            peopleService.save(person);
            return "redirect:/people";
        }
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}