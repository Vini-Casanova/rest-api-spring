package br.com.casanova.apispring.services;

import br.com.casanova.apispring.controllers.PersonController;
import br.com.casanova.apispring.data.vo.v1.PersonVO;
import br.com.casanova.apispring.data.vo.v2.PersonVOV2;
import br.com.casanova.apispring.exceptions.RequiredObjectsNullException;
import br.com.casanova.apispring.exceptions.ResourceNotFoundException;
import br.com.casanova.apispring.mapper.DozerMapper;
import br.com.casanova.apispring.mapper.custom.PersonMapper;
import br.com.casanova.apispring.models.Person;
import br.com.casanova.apispring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper VO2mapper;

    public PersonVO findById(Long id){
        logger.info("Finding person by Id");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        PersonVO vo =  DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll (){
        logger.info("Finding all");
        List<Person> entities = repository.findAll();
        var persons = DozerMapper.parseListObjects(entities, PersonVO.class);
        persons.stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getPrimaryKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return persons;
    }

    public PersonVO create(PersonVO personvo){

        if(personvo == null) throw new RequiredObjectsNullException();

        logger.info("Creating person");
        Person personEntity = DozerMapper.parseObject(personvo, Person.class);
        PersonVO vo = DozerMapper.parseObject(repository.save(personEntity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getPrimaryKey())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createV2 (PersonVOV2 personvo2){
        logger.info("Creating person with V2");
        Person personEntity = VO2mapper.convertVO2ToEntity(personvo2);
        repository.save(personEntity);
        return personvo2;
    }

    public void delete (Long id){

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }


    public PersonVO update(PersonVO personvo){

        if(personvo == null) throw new RequiredObjectsNullException();
        logger.info("Updating person");
        Person entity = repository.findById(personvo.getPrimaryKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAddress(personvo.getAddress());
        entity.setName(personvo.getName());
        PersonVO vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getPrimaryKey())).withSelfRel());
        return vo;
    }














}
