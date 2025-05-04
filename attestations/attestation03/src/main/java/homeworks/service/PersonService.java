package homeworks.service;

import homeworks.dto.PersonRequestDto;
import homeworks.dto.PersonResponseDto;

import java.util.List;

public interface PersonService {
    List<PersonResponseDto> getAllPersons();

    PersonResponseDto findPersonById(Long id);

    PersonResponseDto findPersonByFio(String fio);

    PersonResponseDto addPerson(PersonRequestDto personRequestDto);

    void deletePersonById(Long id);
}
