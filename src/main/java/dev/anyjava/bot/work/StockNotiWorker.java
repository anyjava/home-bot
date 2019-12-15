package dev.anyjava.bot.work;

import dev.anyjava.bot.domain.stock.StockNoti;
import dev.anyjava.bot.domain.stock.StockNotiRepository;
import dev.anyjava.bot.infra.stock.StockApiAdaptor;
import dev.anyjava.bot.infra.stock.StockMessageBuilder;
import dev.anyjava.bot.line.LineMessageBroker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Component
public class StockNotiWorker {

    private final StockNotiRepository stockNotiRepository;
    private final StockApiAdaptor stockApiAdaptor;
    private final StockMessageBuilder stockMessageBuilder;
    private final LineMessageBroker lineMessageBroker;

    @Scheduled(cron = "10 9 * * * *")
    public void run() {
        stockNotiRepository.findAll().stream()
                .map(StockNoti::getCode)
                .map(Arrays::asList)
                .map(stockApiAdaptor::findAllByCodes)
                .flatMap(Collection::stream)
                .map(stockMessageBuilder::build)
                .forEach(lineMessageBroker::pushMessage);
    }
}
