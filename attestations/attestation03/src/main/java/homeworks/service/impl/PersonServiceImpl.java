package homeworks.service.impl;

import homeworks.dto.PersonRequestDto;
import homeworks.dto.PersonResponseDto;
import homeworks.mapper.PersonRequestMapper;
import homeworks.mapper.PersonResponseMapper;
import homeworks.repository.PersonRepository;
import homeworks.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonRequestMapper personRequestMapper;
    private final PersonResponseMapper personResponseMapper;



    @Override
    public List<PersonResponseDto> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(personResponseMapper::toPersonResponseDto)
                .toList();
    }

    @Override
    public PersonResponseDto findPersonById(Long id) {
        return personResponseMapper.toPersonResponseDto(
                personRepository.findById(id).orElse(null)
        );
    }

    @Override
    public PersonResponseDto findPersonByFio(String fio) {
        List<PersonResponseDto> personResponseDtoList = personRepository.findPersonByFio(fio)
                .stream()
                .map(personResponseMapper::toPersonResponseDto)
                .toList();
        if(personResponseDtoList.isEmpty()) {
            return null;
        }
        return personResponseDtoList.get(0);
    }

    @Override
    public PersonResponseDto addPerson(PersonRequestDto personRequestDto) {
        return personResponseMapper.toPersonResponseDto(
                personRepository.save(
                        personRequestMapper.toEntity(personRequestDto)
                )
        );
    }

    @Override
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
}
