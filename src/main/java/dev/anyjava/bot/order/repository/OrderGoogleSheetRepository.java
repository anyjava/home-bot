package dev.anyjava.bot.order.repository;

import dev.anyjava.bot.infra.order.OrderSheetClient;
import dev.anyjava.bot.order.domain.Order;
import dev.anyjava.bot.order.domain.OrderForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderGoogleSheetRepository implements OrderRepository {

    private static final String RANGE = "A1:R999";
    private final OrderSheetClient orderSheetClient;

    @Value("${google.sheets.orderList}")
    private String spreadsheetId;

    @Value("${google.apiKey}")
    private String apiKey;

    @Override
    public List<Order> findByPhoneNumber(String phoneNumber) {
        return findAll().stream()
                .filter(v -> v.getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAll() {
        OrderForm sheets = orderSheetClient.getSheets(spreadsheetId, RANGE, apiKey);
        return sheets.getValues().stream()
                .filter(Order::acceptedOrder)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByOrderNumber(String orderName) {
        return findAll().stream()
                .filter(v -> v.getName().equals(orderName))
                .collect(Collectors.toList());
    }
}
