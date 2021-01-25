package dev.anyjava.bot.order.service;

import dev.anyjava.bot.order.domain.Order;
import dev.anyjava.bot.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepository orderRepository;

    public List<Order> findAllReservation(LocalDate baseDate) {
        return orderRepository.findAll().stream()
                .filter(v -> v.getDeliveryStartDate().isAfter(baseDate))
                .collect(Collectors.toList());
    }
}
