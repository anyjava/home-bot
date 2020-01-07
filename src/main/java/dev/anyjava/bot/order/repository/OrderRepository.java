package dev.anyjava.bot.order.repository;

import dev.anyjava.bot.infra.order.OrderSheetClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderRepository {

    private static final String RANGE = "A2:R999";
    private final OrderSheetClient orderSheetClient;

    @Value("${google.sheets.orderList}")
    private String spreadsheetId;

    @Value("${google.apiKey}")
    private String apiKey;

    public List<Order> findByPhoneNumber(String phoneNumber) {

        OrderForm sheets = orderSheetClient.getSheets(spreadsheetId, RANGE, apiKey);
        return sheets.getValues().stream()
                .filter(Order::acceptedOrder)
                .collect(Collectors.toList());

    }
}
