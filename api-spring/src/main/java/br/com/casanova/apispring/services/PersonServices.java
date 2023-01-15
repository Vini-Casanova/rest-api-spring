package br.com.casanova.apispring.services;

import br.com.casanova.apispring.data.vo.v1.PersonVO;
import br.com.casanova.apispring.exceptions.ResourceNotFoundException;
import br.com.casanova.apispring.mapper.DozerMapper;
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

    public PersonVO findById(Long id){
        logger.info("Finding person by Id");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll (){
        logger.info("Finding all");
        List<Person> entities = repository.findAll();
        return DozerMapper.parseListObjects(entities, PersonVO.class);
    }

    public PersonVO create(PersonVO personvo){
        logger.info("Creating person");
        Person personEntity = DozerMapper.parseObject(personvo, Person.class);
        repository.save(personEntity);
        return personvo;
    }

    public void delete (Long id){

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }


    public PersonVO update(PersonVO personvo){
        logger.info("Updating person");
        Person entity = repository.findById(personvo.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAddress(personvo.getAddress());
        entity.setName(personvo.getName());
        repository.save(entity);

        return personvo;
    }














}
