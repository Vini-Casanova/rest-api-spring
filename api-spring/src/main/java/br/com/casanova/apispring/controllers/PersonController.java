package br.com.casanova.apispring.controllers;

import br.com.casanova.apispring.models.Person;
import br.com.casanova.apispring.services.PersonServices;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable Long id)throws Exception{
            return services.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findById()throws Exception{
        return services.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody Person person)throws Exception{
        services.create(person);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person) throws Exception {
        return services.update(person);
    }

    @DeleteMapping(value = ("/{id}"))
    public ResponseEntity<?> delete (@PathVariable Long id) throws Exception {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }





}
