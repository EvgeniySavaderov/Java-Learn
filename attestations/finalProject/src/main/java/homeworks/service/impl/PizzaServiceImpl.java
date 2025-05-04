package homeworks.service.impl;

import homeworks.dto.*;
import homeworks.mapper.*;
import homeworks.model.Person;
import homeworks.model.PizzaBase;
import homeworks.model.PizzaOrder;
import homeworks.repository.PersonRepository;
import homeworks.repository.PizzaBaseRepository;
import homeworks.repository.PizzaOrderRepository;
import homeworks.service.PizzaService;
import homeworks.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {
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

    private final PizzaBaseRepository pizzaBaseRepository;
    private final PizzaBaseRequestMapper pizzaBaseRequestMapper;
    private final PizzaBaseResponseMapper pizzaBaseResponseMapper;

    @Override
    public List<PizzaBaseResponseDto> getAllPizzaBases() {
        return pizzaBaseRepository.findAll()
                .stream().map(pizzaBaseResponseMapper::toPizzaBaseResponseDto)
                .toList();
    }

    @Override
    public PizzaBaseResponseDto findPizzaBaseById(Long id) {
        return pizzaBaseResponseMapper.toPizzaBaseResponseDto(
                pizzaBaseRepository.findById(id).orElseThrow()
        );
    }

    @Override
    public PizzaBaseResponseDto addPizzaBase(PizzaBaseRequestDto pizzaBaseRequestDto) {
        return pizzaBaseResponseMapper.toPizzaBaseResponseDto(
                pizzaBaseRepository.save(
                        pizzaBaseRequestMapper.toEntity(pizzaBaseRequestDto)
                )
        );
    }

    private final PizzaOrderRepository pizzaOrderRepository;
    private final PizzaOrderRequestMapper pizzaOrderRequestMapper;
    private final PizzaOrderResponseMapper pizzaOrderResponseMapper;

    @Override
    public List<PizzaOrderResponseDto> getAllPizzaOrders() {
        return pizzaOrderRepository.findAll()
                .stream().map(pizzaOrderResponseMapper::toPizzaOrderResponseDto)
                .toList();
    }

    @Override
    public PizzaOrderResponseDto findPizzaOrderById(Long id) {
        return pizzaOrderResponseMapper.toPizzaOrderResponseDto(
                pizzaOrderRepository.findById(id).orElse(null)
        );
    }

    @Override
    public PizzaOrderResponseDto addPizzaOrder(PizzaOrderRequestDto pizzaOrderRequestDto) {
        return pizzaOrderResponseMapper.toPizzaOrderResponseDto(
                pizzaOrderRepository.save(
                        pizzaOrderRequestMapper.toEntity(pizzaOrderRequestDto)
                )
        );
    }

    @Override
    public void deletePizzaOrderById(Long id) {
        pizzaOrderRepository.deleteById(id);
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
