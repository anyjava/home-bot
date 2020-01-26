package dev.anyjava.bot.order.service;

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
                .filter(this::acceptDeliveryCompany)
                .map(this::convertDTO)
                .collect(Collectors.toList());
    }

    private boolean acceptDeliveryCompany(Order order) {
        return order.getDeliveryInvoice().getDeliveryCompanyType().isAbleTracking();
    }

    private String convertDTO(Order order) {
        deliveryQueryService.findByTrackId(order);
        return String.format("rowId=%d, status=%s ", order.getRowId(), order.getStatus().getDesc());
    }
}
