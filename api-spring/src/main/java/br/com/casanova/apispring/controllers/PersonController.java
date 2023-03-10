package br.com.casanova.apispring.controllers;

import br.com.casanova.apispring.data.vo.v1.PersonVO;
import br.com.casanova.apispring.data.vo.v2.PersonVOV2;
import br.com.casanova.apispring.services.PersonServices;
import br.com.casanova.apispring.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name="People", description = "EndPoints for Managing People")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value = "/{id}" , produces = {MediaType.APPLICATION_JSON,
                                                MediaType.APPLICATION_XML,
                                                MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a Person", description = "Finds a Person based on the id",
            tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = {@Content}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content}),
    })
    public PersonVO findById(@PathVariable Long id){
            return services.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all People", description = "Finds all People",
            tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content}),
    })
    public List<PersonVO> findAll(){
        return services.findAll();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                 produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new Person", description = "Adds a new Person by passing in a JSON, XML, X-YAML representation of the Person",
            tags = {"People"}, responses = {
            @ApiResponse(description = "Added", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content}),
    })
    public PersonVO save(@RequestBody PersonVO person){
        return services.create(person);
    }

    @PostMapping(value = "/v2",consumes = {MediaType.APPLICATION_JSON,
                                             MediaType.APPLICATION_XML,
                                             MediaType.APPLICATION_YML})
    public void saveV2(@RequestBody PersonVOV2 person){
        services.createV2(person);
    }


    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a Person", description = "Updates a Person by passing in a JSON, XML, X-YAML representation of the Person and the id from the person been updated",
            tags = {"People"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content}),
    })
    public PersonVO update(@RequestBody PersonVO person){
        return services.update(person);
    }

    @DeleteMapping(value = ("/{id}"))
    @Operation(summary = "Deletes a Person", description = "Deletes a Person by passing a id from the person",
            tags = {"People"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<?> delete (@PathVariable Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }





}
