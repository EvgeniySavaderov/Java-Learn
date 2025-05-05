package homeworks.service.impl;

import homeworks.dto.PizzaBaseRequestDto;
import homeworks.dto.PizzaBaseResponseDto;
import homeworks.mapper.PizzaBaseRequestMapper;
import homeworks.mapper.PizzaBaseResponseMapper;
import homeworks.repository.PizzaBaseRepository;
import homeworks.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

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
}
