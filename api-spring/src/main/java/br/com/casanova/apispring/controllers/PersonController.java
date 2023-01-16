package br.com.casanova.apispring.controllers;

import br.com.casanova.apispring.data.vo.v1.PersonVO;
import br.com.casanova.apispring.data.vo.v2.PersonVOV2;
import br.com.casanova.apispring.services.PersonServices;
import br.com.casanova.apispring.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value = "/{id}" , produces = {MediaType.APPLICATION_JSON,
                                                MediaType.APPLICATION_XML,
                                                MediaType.APPLICATION_YML})
    public PersonVO findById(@PathVariable Long id)throws Exception{
            return services.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_YML})
    public List<PersonVO> findAll()throws Exception{
        return services.findAll();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML,
                             MediaType.APPLICATION_YML})
    public void save(@RequestBody PersonVO person)throws Exception{
        services.create(person);
    }

    @PostMapping(value = "/v2",consumes = {MediaType.APPLICATION_JSON,
                                             MediaType.APPLICATION_XML,
                                             MediaType.APPLICATION_YML})
    public void saveV2(@RequestBody PersonVOV2 person)throws Exception{
        services.createV2(person);
    }


    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO update(@RequestBody PersonVO person) throws Exception {
        return services.update(person);
    }

    @DeleteMapping(value = ("/{id}"))
    public ResponseEntity<?> delete (@PathVariable Long id) throws Exception {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }





}
