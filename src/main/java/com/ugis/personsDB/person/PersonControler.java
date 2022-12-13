package com.ugis.personsDB.person;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/person/")
public class PersonControler {

    private final PersonService personService;

    @Autowired
    public PersonControler(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "all")
    @ResponseBody
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping(path = "email/{personEmail}")
    @ResponseBody
    public Optional<Person> getPersonByEmail(@PathVariable("personEmail") String personEmail) {
        return personService.getPersonByEmail(personEmail);
    }

    @DeleteMapping(path = "email/{personEmail}")
    @ResponseBody
    public void deletePersonByEmail(@PathVariable("personEmail") String personEmai){
        personService.deletePersonByEmail(personEmai);
    }

    @PutMapping(path = "email/{email}")
    @ResponseBody
    public void updatePersonByEmail(@ModelAttribute Person person){
        personService.updatePersonByEmail(person);
    }

    @PostMapping
    @ResponseBody
    public void registerNewPerson(@RequestBody Person person){
        personService.addNewPerson(person);
    }




}
