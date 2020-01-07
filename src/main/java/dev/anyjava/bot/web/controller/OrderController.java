package dev.anyjava.bot.web.controller;

import dev.anyjava.bot.order.repository.Order;
import dev.anyjava.bot.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/orders/by-phone-number")
    public List<Order> getOrderList(@RequestParam String phoneNumber) {
        return orderRepository.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/api/orders/by-order-name")
    public List<Order> getOrderListByName(@RequestParam String orderName) {
        return orderRepository.findByOrderNumber(orderName);
    }

    @GetMapping("/api/orders")
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }
}
