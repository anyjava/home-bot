package dev.anyjava.bot.web.controller;

import dev.anyjava.bot.order.domain.DepositType;
import dev.anyjava.bot.order.domain.Order;
import dev.anyjava.bot.order.repository.OrderRepository;
import dev.anyjava.bot.order.service.DeliveryCheckService;
import dev.anyjava.bot.order.service.OrderQueryService;
import dev.anyjava.bot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class OrderAdminController {

    private final OrderRepository orderRepository;
    private final DeliveryCheckService deliveryCheckService;
    private final OrderQueryService orderQueryService;

    @GetMapping("/orders/by-delivery-date")
    public List<DeliveryInvoiceDTO> getOrderListByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deliveryDate) {
        return orderRepository.findByDeliveryDate(deliveryDate).stream()
                .map(this::buildDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/order/reservations")
    public List<OrderReservationDTO> getOrderReservation(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate baseDate) {
        if (Objects.isNull(baseDate)) {
            baseDate = LocalDate.now();
        }

        Map<LocalDate, Integer> map = orderQueryService.findAllReservation(baseDate).stream()
                .collect(Collectors.toMap(Order::getDeliveryStartDate, Order::getTotalQuantity, Integer::sum));

        return map.keySet().stream()
                .map(v -> new OrderReservationDTO(v, map.get(v)))
                .sorted()
                .collect(Collectors.toList());
    }

    @GetMapping("/deliveries/check")
    public List<String> checkDelivery() {
        return deliveryCheckService.exec();
    }

    private DeliveryInvoiceDTO buildDTO(Order order) {
        return DeliveryInvoiceDTO.builder()
                .orderName(order.getName())
                .deliveryStartDate(order.getDeliveryDest().getDeliveryStartDate())
                .deliveryDest(order.getDeliveryDest())
                .memo(order.getMemo2())
                .items(order.getItems())
                .deliveryInvoiceSmsDTO(DeliveryInvoiceSmsDTO.from(order))
                .build();
    }

    @GetMapping("/sales/summary")
    public SalesSummaryResponse getSalesSummary() {
        final Map<DepositType, SalesSummaryDTO> map = orderRepository.findAll().stream()
                .map(order -> Pair.of(order.getDepositType().get(), SalesSummaryDTO.from(order)))
                .collect(Collectors.groupingBy(Pair::getFirst, Collectors.mapping(Pair::getSecond, Collectors.reducing(
                        SalesSummaryDTO.zero(), SalesSummaryDTO::add))));
        return new SalesSummaryResponse(map);
    }
}
