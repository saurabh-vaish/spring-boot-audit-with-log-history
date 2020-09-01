package com.app.service.impl;

import com.app.models.Person;
import com.app.dto.PersonDTO;
import com.app.repository.PersonRepository;
import com.app.service.PersonService;
import com.app.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    private final PersonRepository personRepository;
    private String response;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findByText(String id, String name, String lastName) {
        Integer personId = Utilities.isInteger(id);
        return personRepository.findByIdOrFirstNameOrLastName(personId, name, lastName);
    }

    @Override
    public Person findById(int id) {
        return personRepository.findById(id).get();
    }

    @Override
    public String save(PersonDTO personDTO) {
        response = "Person saved!";
        personRepository.save(new Person(personDTO));
        logger.info(response);
        return response;
    }

    @Override
    public String update(PersonDTO personDTO) {
        response = "Person updated!";
        Person person = personRepository.findById(personDTO.getId()).get();
        person = this.updatePerson(person, personDTO);
        personRepository.save(person);
        logger.info(response);
        return response;
    }

    @Override
    public String deleteById(int id) {
        response = "Person deleted!";
        personRepository.deleteById(id);
        logger.info(response);
        return response;
    }

    private Person updatePerson(Person person, PersonDTO personDTO) {
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        return person;
    }

}
