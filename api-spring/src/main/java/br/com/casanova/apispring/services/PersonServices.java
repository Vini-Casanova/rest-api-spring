package br.com.casanova.apispring.services;

import br.com.casanova.apispring.exceptions.ResourceNotFoundException;
import br.com.casanova.apispring.models.Person;
import br.com.casanova.apispring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public Person findById(Long id){
        logger.info("Finding person by Id");

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public List<Person> findAll (){
        logger.info("Finding all");
        return repository.findAll();
    }

    public Person create(Person person){
        logger.info("Creating person");
        return repository.save(person);
    }

    public void delete (Long id){

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }


    public Person update(Person person){
        logger.info("Updating person");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setAddress(person.getAddress());
        entity.setName(person.getName());

        return repository.save(entity);
    }














}
