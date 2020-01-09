package dev.anyjava.bot.web.controller;

import dev.anyjava.bot.order.domain.Order;
import dev.anyjava.bot.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class OrderAdminController {

    private final OrderRepository orderRepository;

    @GetMapping("/orders/by-delivery-date")
    public List<DeliveryInvoiceDTO> getOrderListByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deliveryDate) {
        return orderRepository.findByDeliveryDate(deliveryDate).stream()
                .map(this::buildDTO)
                .collect(Collectors.toList());
    }

    private DeliveryInvoiceDTO buildDTO(Order order) {
        return DeliveryInvoiceDTO.builder()
                .orderName(order.getName())
                .deliveryStartDate(order.getDeliveryDest().getDeliveryStartDate())
                .deliveryDest(order.getDeliveryDest())
                .memo(order.getMemo2())
                .items(order.getItems())
                .build();
    }
}
