package com.learning.courses.controller;

import com.learning.courses.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

public class PersonControllerIT extends AbstractIntegrationTest {

    @Test
    @Sql("/sql/createPersonInit.sql")
    void addContact_shouldReturn200AndContactDTO_whenValidRequest() {
    }

    @Test
    @Sql("/sql/createPersonInit.sql")
    void addContact_shouldReturn400_whenEmailFormatIsInvalid() {
    }

    @Test
    void addContact_shouldReturn404_whenPersonDoesNotExist() {
    }

    @Test
    @Sql("/sql/createPersonWithContactInit.sql")
    void deleteContact_shouldReturn204AndRemoveAllContactData_whenPersonExists() {
    }

    @Test
    void deleteContact_shouldReturn404_whenPersonDoesNotExist() {
    }

    @Test
    @Sql("/sql/createPersonWithContactInit.sql")
    void getPerson_shouldReturn200WithContactData_whenPersonHasContact() {
    }
}

