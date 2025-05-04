package homeworks.service.impl;

import homeworks.dto.PizzaBaseResponseDto;
import homeworks.mapper.*;
import homeworks.model.Person;
import homeworks.model.PizzaOrder;
import homeworks.model.PizzaBase;

import homeworks.dto.PersonResponseDto;
import homeworks.dto.PizzaOrderRequestDto;
import homeworks.repository.PersonRepository;
import homeworks.repository.PizzaBaseRepository;
import homeworks.service.PersonAddOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import homeworks.utils.Helper;

@Service
@RequiredArgsConstructor
public class PersonAddOrderServiceImpl implements PersonAddOrderService {
    private final PersonRepository personRepository;
    private final PersonResponseMapper personResponseMapper;
    private final PizzaBaseRepository pizzaBaseRepository;
    private final PizzaBaseResponseMapper pizzaBaseResponseMapper;
    private final PizzaOrderRequestMapper pizzaOrderRequestMapper;

    public PersonResponseDto findPersonById(Long id) {
        return personResponseMapper.toPersonResponseDto(
                personRepository.findById(id).orElse(null)
        );
    }

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

    public PizzaBaseResponseDto findPizzaByName(String name) {
        List<PizzaBaseResponseDto> pizzaBaseResponseDtoList = pizzaBaseRepository.findPizzaByName(name)
                .stream()
                .map(pizzaBaseResponseMapper::toPizzaBaseResponseDto)
                .toList();
        if(pizzaBaseResponseDtoList.isEmpty()) {
            return null;
        }
        return pizzaBaseResponseDtoList.get(0);
    }

    @Override
    public PersonResponseDto addOrderForPersonById(Long id, PizzaOrderRequestDto pizzaOrderRequestDto) {


        if(pizzaOrderRequestDto.size().size() != pizzaOrderRequestDto.amount().size()
                || pizzaOrderRequestDto.amount().size() != pizzaOrderRequestDto.pizzaBaseList().size()){
            return null;
        }

        PersonResponseDto foundPerson = findPersonById(id);
        if(foundPerson == null) {
            return null;
        }

        Person person = personResponseMapper.toEntity(foundPerson);
        PizzaOrder pizzaOrder = pizzaOrderRequestMapper.toEntity(pizzaOrderRequestDto);
        List<PizzaBase> pizzaBaseList = pizzaOrder.getPizzaBaseList()
                .stream()
                .map(pizza -> pizza.getId()==null ?
                        pizzaBaseResponseMapper.toEntity(findPizzaByName(pizza.getName())) :
                        pizzaBaseRepository.findById(pizza.getId()).orElse(null))
                .toList();
        pizzaOrder.setCost(Helper.countCost(pizzaOrder.getSize(), pizzaOrder.getAmount(), pizzaBaseList));
        pizzaOrder.setPizzaBaseList(pizzaBaseList.stream()
                .filter(Objects::nonNull)
                .toList());
        if(pizzaOrder.getPizzaBaseList().isEmpty()) {
            return null;
        }
        person.getPizzaOrderList().add(pizzaOrder);

        return personResponseMapper.toPersonResponseDto(
                personRepository.save(person)
        );
    }

    @Override
    public PersonResponseDto addOrderForPersonByFio(String fio, PizzaOrderRequestDto pizzaOrderRequestDto) {
        if(pizzaOrderRequestDto.size().size() != pizzaOrderRequestDto.amount().size()
                || pizzaOrderRequestDto.amount().size() != pizzaOrderRequestDto.pizzaBaseList().size()){
            return null;
        }

        PersonResponseDto foundPerson = findPersonByFio(fio);
        if(foundPerson == null) {
            return null;
        }

        Person person = personResponseMapper.toEntity(foundPerson);
        PizzaOrder pizzaOrder = pizzaOrderRequestMapper.toEntity(pizzaOrderRequestDto);
        List<PizzaBase> pizzaBaseList = pizzaOrder.getPizzaBaseList()
                .stream()
                .map(pizza -> pizza.getId()==null ?
                        pizzaBaseResponseMapper.toEntity(findPizzaByName(pizza.getName())) :
                        pizzaBaseRepository.findById(pizza.getId()).orElse(null))
                .toList();
        pizzaOrder.setCost(Helper.countCost(pizzaOrder.getSize(), pizzaOrder.getAmount(), pizzaBaseList));
        pizzaOrder.setPizzaBaseList(pizzaBaseList.stream()
                .filter(Objects::nonNull)
                .toList());
        if(pizzaOrder.getPizzaBaseList().isEmpty()) {
            return null;
        }
        person.getPizzaOrderList().add(pizzaOrder);

        return personResponseMapper.toPersonResponseDto(
                personRepository.save(person)
        );
    }
}
