package homeworks.service;

import homeworks.model.Orders;
import homeworks.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    @Autowired
    private final OrdersRepository ordersRepository;

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    public Orders findById(long id) {
        return ordersRepository.findById(id)
                .orElseThrow();
    }

    public Orders save(Orders order) {
        return ordersRepository.save(order);
    }

    public void clear() {
        ordersRepository.deleteAll();
    }

    public List<Orders> findByDate(LocalDate date) {
        return ordersRepository.findByDateOrder(date);
    }
}
