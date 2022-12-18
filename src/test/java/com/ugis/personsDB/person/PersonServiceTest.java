package com.ugis.personsDB.person;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock private PersonRepository personRepository;
    private PersonService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PersonService(personRepository);
    }

    @Test
    void canGetPersons() {
        // when
        underTest.getPersons();
        // then
        verify(personRepository).findAll();

    }

    @Test
    void canAddNewPerson() {
        // given
        Person person = new Person(
                "Ugis",
                "ugis@mail.lv",
                45
        );
        // when
        underTest.addNewPerson(person);
        // then
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);

        verify(personRepository).save(personArgumentCaptor.capture());

        Person capturedPerson = personArgumentCaptor.getValue();

        assertThat(capturedPerson).isEqualTo(person);
    }

    @Test
    @Disabled
    void deletePersonByEmail() {
    }
}