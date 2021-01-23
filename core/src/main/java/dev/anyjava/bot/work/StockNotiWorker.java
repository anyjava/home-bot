package dev.anyjava.bot.work;

import dev.anyjava.bot.stock.domain.StockNoti;
import dev.anyjava.bot.stock.domain.StockNotiRepository;
import dev.anyjava.bot.stock.service.StockApiAdaptor;
import dev.anyjava.bot.stock.service.StockMessageBuilder;
import dev.anyjava.bot.line.LineNotiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
@Profile("!test")
@RequiredArgsConstructor
@Component
public class StockNotiWorker {

    private final StockNotiRepository stockNotiRepository;
    private final StockApiAdaptor stockApiAdaptor;
    private final StockMessageBuilder stockMessageBuilder;
    private final LineNotiService lineNotiService;

    @Scheduled(cron = "0 10 9 * * MON-FRI", zone = "JST")
    public void run() {
        try {
            stockNotiRepository.findAll().stream()
                    .map(StockNoti::getCode)
                    .map(Arrays::asList)
                    .map(stockApiAdaptor::findAllByCodes)
                    .flatMap(Collection::stream)
                    .map(stockMessageBuilder::build)
                    .forEach(lineNotiService::sendToGroupChat);
        } catch (Exception e) {
            log.error("failed to getting stock price, message={}", e.getMessage(), e);
            lineNotiService.sendToAdmin("failed to getting stock price, message=" + e.getMessage());
        }
    }
}
