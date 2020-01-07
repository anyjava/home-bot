package dev.anyjava.bot.web.controller;

import dev.anyjava.bot.order.repository.OrderRepository;
import dev.anyjava.bot.stock.service.StockMessageBuilder;
import dev.anyjava.bot.stock.service.StockQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Profile("local")
@RequiredArgsConstructor
@RestController
public class LineWebHookMockingController {

    private final StockQueryService stockQueryService;
    private final StockMessageBuilder stockMessageBuilder;
    private final OrderRepository orderRepository;

    @GetMapping("/line/message")
    public String handleMessage(@RequestParam String type, @RequestParam(required = false) String message) {
        log.info("line web hook testing!, type={}, message={}", type, message);
        return stockQueryService.findByName("카카오")
                .map(stockMessageBuilder::build)
                .orElse("");
    }

    @GetMapping("/api/orders/by-phone-number")
    public String getOrderList(@RequestParam String phoneNumber) {
        return orderRepository.findByPhoneNumber(phoneNumber).get(0).toString();
    }
}
