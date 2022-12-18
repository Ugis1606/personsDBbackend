package com.ugis.personsDB.person;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShoudChekIfPersonExistsByEmail() {
        // given
        Person person = new Person(
                "Ugis",
                "ugis@mail.lv",
                45
        );
        underTest.save(person);
        // when
        boolean exists = underTest.existsById(person.getId());
        // then
        assertThat(exists).isTrue();
    }

    @Test
    void itShoudChekIfPersonDoesNotExistsByEmail() {
        // given

        // when
        boolean exists = underTest.existsById(1L);
        // then
        assertThat(exists).isFalse();
    }







}