package dev.anyjava.bot.order.service;

import dev.anyjava.bot.order.domain.DeliveryStatus;
import dev.anyjava.bot.order.domain.Order;
import dev.anyjava.bot.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeliveryCheckService {

    private final DeliveryQueryService deliveryQueryService;
    private final OrderRepository orderRepository;

    public List<String> exec() {
        return orderRepository.findAll().stream()
                .filter(Order::isShipping)
                .map(this::convertDTO)
                .collect(Collectors.toList());
    }

    private String convertDTO(Order order) {
        DeliveryStatus deliveryStatus = deliveryQueryService.findByTrackId(order.getDeliveryInvoice());
        return String.format("rowId=%d, status=%s ", order.getRowId(), deliveryStatus.name());
    }
}
