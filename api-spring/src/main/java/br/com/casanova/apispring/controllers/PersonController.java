package br.com.casanova.apispring.controllers;

import br.com.casanova.apispring.models.Person;
import br.com.casanova.apispring.services.PersonServices;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable Long id)throws Exception{
            return services.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findById()throws Exception{
        return services.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person) throws Exception {
        return services.update(person);
    }

    @RequestMapping(value = ("/{id}"),method = RequestMethod.DELETE)
    public void update(@PathVariable Long id) throws Exception {
        services.delete(id);
    }





}
