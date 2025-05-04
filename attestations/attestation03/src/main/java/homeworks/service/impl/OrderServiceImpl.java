package homeworks.service.impl;

import homeworks.dto.PizzaOrderRequestDto;
import homeworks.dto.PizzaOrderResponseDto;
import homeworks.mapper.PizzaOrderRequestMapper;
import homeworks.mapper.PizzaOrderResponseMapper;
import homeworks.repository.PizzaOrderRepository;
import homeworks.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
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
}
