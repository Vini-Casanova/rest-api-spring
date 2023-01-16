package br.com.casanova.apispring.mapper.custom;

import br.com.casanova.apispring.data.vo.v2.PersonVOV2;
import br.com.casanova.apispring.models.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVO2(Person person){
        PersonVOV2 personVOV2 = new PersonVOV2();
        personVOV2.setId(person.getId());
        personVOV2.setName(person.getName());
        personVOV2.setBirthday(new Date());
        personVOV2.setAddress(personVOV2.getAddress());
        return  personVOV2;
    }

    public Person convertVO2ToEntity(PersonVOV2 person){
        Person personVOV2 = new Person();
        personVOV2.setId(person.getId());
        personVOV2.setName(person.getName());
        personVOV2.setAddress(personVOV2.getAddress());
        return  personVOV2;
    }
}
