package homeworks.service.impl;

import homeworks.dto.PersonRequestDto;
import homeworks.dto.PersonResponseDto;
import homeworks.mapper.PersonRequestMapper;
import homeworks.mapper.PersonResponseMapper;
import homeworks.model.Person;
import homeworks.repository.PersonRepository;
import homeworks.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = PersonServiceImpl.class)
class PersonServiceImplTest {
    @Autowired
    private PersonService personService;
    @MockitoBean
    private PersonRepository personRepository;
    @MockitoBean
    private PersonRequestMapper personRequestMapper;
    @MockitoBean
    private PersonResponseMapper personResponseMapper;

    @Test
    void getAllPersons() {
        List<Person> personEntities = new ArrayList<>();
        personEntities.add(new Person(1L, "Иванов Петр Алексеевич", List.of()));
        personEntities.add(new Person(2L, "Стеклов Иван Петрович", List.of()));
        PersonResponseDto personResponseDto = new PersonResponseDto(1L, "Иванов Петр Алексеевич", List.of());
        Mockito.when(personRepository.findAll()).thenReturn(personEntities);
        Mockito.when(personResponseMapper.toPersonResponseDto(any(Person.class))).thenReturn(personResponseDto);

        List<PersonResponseDto> personResponseList = personService.getAllPersons();

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(personResponseList);
            Assertions.assertEquals(2, personResponseList.size());
        });
    }
    @Test
    void findPersonById() {
        Person foundPerson = new Person(1L, "Иванов Петр Алексеевич", List.of());
        PersonResponseDto foundPersonResponse = new PersonResponseDto(1L, "Иванов Петр Алексеевич", List.of());

        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(foundPerson));
        Mockito.when(personResponseMapper.toPersonResponseDto(foundPerson)).thenReturn(foundPersonResponse);

        PersonResponseDto personResponseDto = personService.findPersonById(1L);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(personResponseDto);
            Assertions.assertEquals(1L, personResponseDto.id());
        });
    }

    @Test
    void addPerson() {
        PersonRequestDto addPersonRequest = new PersonRequestDto(null, "Кузнецов Иван Алексеевич", List.of());
        Person person = new Person(null, "Кузнецов Иван Алексеевич", List.of());
        Person addedPerson = new Person(3L, "Кузнецов Иван Алексеевич", List.of());
        PersonResponseDto addedpersonResponse = new PersonResponseDto(3L, "Кузнецов Иван Алексеевич", List.of());

        Mockito.when(personRequestMapper.toEntity(addPersonRequest)).thenReturn(person);
        Mockito.when(personRepository.save(person)).thenReturn(addedPerson);
        Mockito.when(personResponseMapper.toPersonResponseDto(addedPerson)).thenReturn(addedpersonResponse);

        PersonResponseDto personResponseDto = personService.addPerson(addPersonRequest);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(personResponseDto);
            Assertions.assertEquals(3L, personResponseDto.id());
        });
    }

    @Test
    void findPersonByFio() {
        Person foundPerson = new Person(1L, "Иванов Петр Алексеевич", List.of());
        PersonResponseDto foundPersonResponse = new PersonResponseDto(1L, "Иванов Петр Алексеевич", List.of());

        Mockito.when(personRepository.findPersonByFio("Иванов Петр Алексеевич")).thenReturn(List.of(foundPerson));
        Mockito.when(personResponseMapper.toPersonResponseDto(foundPerson)).thenReturn(foundPersonResponse);

        PersonResponseDto personResponseDto = personService.findPersonByFio("Иванов Петр Алексеевич");

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(personResponseDto);
            Assertions.assertEquals("Иванов Петр Алексеевич", personResponseDto.fio());
        });
    }
}