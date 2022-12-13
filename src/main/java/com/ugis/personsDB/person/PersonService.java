package com.ugis.personsDB.person;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    // GET by email
    public Optional<Person> getPersonByEmail(String email) {
        Optional<Person> personOptional = personRepository.findPersonByEmail(email);
        if(personOptional.isEmpty()) throw new IllegalStateException("no such person");

        return personOptional;
    }

    // DELETE by email
    public void deletePersonByEmail(String email) {
        Optional<Person> personOptional = personRepository.findPersonByEmail(email);
        if(personOptional.isEmpty()) throw new IllegalStateException("no such person");

        personRepository.delete(personOptional.get());
    }

    // PUT by email
    @Transactional
    public void updatePersonByEmail(Person person) {
        Optional<Person> personOptional = personRepository.findPersonByEmail(person.getEmail());
        if(personOptional.isEmpty()) throw new IllegalStateException("no such person");

        if(person.getName().length() > 0 && !Objects.equals(personOptional.get().getName(), person.getName())) personOptional.get().setName(person.getName());

        if(person.getAge() > 0 && !Objects.equals(personOptional.get().getAge(), person.getAge())) personOptional.get().setAge(person.getAge());
    }

    // POST by email
    public void addNewPerson(Person person) {
        Optional<Person> personOptional = personRepository.findPersonByEmail(person.getEmail());
        if(personOptional.isPresent()) throw new IllegalStateException("email taken");

        personRepository.save(person);
    }


}
