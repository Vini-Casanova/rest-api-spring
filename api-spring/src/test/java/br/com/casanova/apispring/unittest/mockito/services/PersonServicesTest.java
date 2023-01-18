package br.com.casanova.apispring.unittest.mockito.services;

import br.com.casanova.apispring.data.vo.v1.PersonVO;
import br.com.casanova.apispring.exceptions.RequiredObjectsNullException;
import br.com.casanova.apispring.models.Person;
import br.com.casanova.apispring.repository.PersonRepository;
import br.com.casanova.apispring.services.PersonServices;
import br.com.casanova.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices services;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUpMocks(){
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person entity = input.mockEntity();
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        PersonVO result = services.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertNotNull(result.getAddress());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Name Test0",result.getName());
        assertEquals("Addres Test0",result.getAddress());
    }

    @Test
    void findAll() {
        List<Person> entityList = input.mockEntityList();

        when(repository.findAll()).thenReturn(entityList);

        List<PersonVO> result = services.findAll();

        assertNotNull(result);
        assertEquals(14, result.size());

        assertNotNull(result.get(0).getName());
        assertNotNull(result.get(0).getAddress());
        assertTrue(result.get(0).toString().contains("links: [</api/person/v1/0>;rel=\"self\"]"));
        assertEquals("Name Test0",result.get(0).getName());
        assertEquals("Addres Test0",result.get(0).getAddress());


        assertNotNull(result.get(7).getName());
        assertNotNull(result.get(7).getAddress());
        System.out.println(result.get(0).toString());
        assertTrue(result.get(7).toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
        assertEquals("Name Test7",result.get(7).getName());
        assertEquals("Addres Test7",result.get(7).getAddress());

    }

    @Test
    void create() {
        Person personEntity = input.mockEntity(1);

        Person persisted = personEntity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setPrimaryKey(1L);

        when(repository.save(personEntity)).thenReturn(persisted);

        PersonVO result = services.create(vo);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertNotNull(result.getName());
        assertNotNull(result.getAddress());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Name Test1",result.getName());
        assertEquals("Addres Test1",result.getAddress());


    }

    @Test
    void delete() {
        fail("Test not implemented");
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectsNullException.class, () -> {
            services.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectsNullException.class, () -> {
            services.update(null);
        });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Person personEntity = input.mockEntity(1);

        Person persisted = personEntity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setPrimaryKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(personEntity));
        when(repository.save(personEntity)).thenReturn(persisted);

        PersonVO result = services.update(vo);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertNotNull(result.getName());
        assertNotNull(result.getAddress());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Name Test1",result.getName());
        assertEquals("Addres Test1",result.getAddress());
    }
}