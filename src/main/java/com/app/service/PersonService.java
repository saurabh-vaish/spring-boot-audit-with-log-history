package com.app.service;

import com.app.dto.PersonDTO;
import com.app.models.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Person findByText(String id, String name, String lastName);

    Person findById(int id);

    String save(PersonDTO personDTO);

    String update(PersonDTO personDTO);

    String deleteById(int id);

}
