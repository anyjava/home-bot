package dev.anyjava.bot.web.controller;

import dev.anyjava.bot.order.domain.Order;
import dev.anyjava.bot.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/orders/by-phone-number")
    public List<Order> getOrderList(@RequestParam String phoneNumber) {
        return orderRepository.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/api/orders/by-order-name")
    public OrderPagerResponse getOrderListByName(@RequestParam String orderName) {
        List<Order> orders = orderRepository.findByOrderNumber(orderName);
        return new OrderPagerResponse(
                orderName,
                orders.stream().map(Order::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add),
                orders.stream().map(OrderPagerDTO::of).collect(Collectors.toList())
                );
    }

    @GetMapping("/api/orders")
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }
}
