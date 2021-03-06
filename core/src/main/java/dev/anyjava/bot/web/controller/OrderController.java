package dev.anyjava.bot.web.controller;

import dev.anyjava.bot.order.domain.Order;
import dev.anyjava.bot.order.repository.OrderRepository;
import dev.anyjava.bot.telegram.TelegramNotiService;
import dev.anyjava.bot.web.dto.OrderPagerDTO;
import dev.anyjava.bot.web.dto.OrderPagerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final TelegramNotiService telegramNotiService;

    @GetMapping("/api/orders/by-phone-number")
    public List<Order> getOrderList(@RequestParam String phoneNumber) {
        return orderRepository.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/api/orders/by-order-name")
    public OrderPagerResponse getOrderListByName(@RequestParam String orderName) {
        List<Order> orders = orderRepository.findByOrderNumber(orderName);

        telegramNotiService.sendToManager("주문내역이 조회 되었습니다. / " + orderName);
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
