package test;

import frame.forms.SurnameNamePatronymicInOneStringForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import person.Person;

import javax.swing.*;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSurnameNamePatronymicInOneStringForm {

    @Test
    public void testGetPerson() {
        SurnameNamePatronymicInOneStringForm form = new SurnameNamePatronymicInOneStringForm();
        form.getSurnameNamePatronymic().setText("Фёдоров Вячеслав Игорьевич");
        Person person = form.getPerson();
        String expectedSurname = "Фёдоров";
        String expectedName = "Вячеслав";
        String expectedPatronymic = "Игорьевич";
        assertAll("names",
                () -> assertEquals(expectedSurname, person.getSurname()),
                () -> assertEquals(expectedName, person.getName()),
                () -> assertEquals(expectedPatronymic, person.getPatronymic())
                );
    }
}

