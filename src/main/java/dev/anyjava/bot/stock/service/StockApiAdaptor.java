package dev.anyjava.bot.stock.service;

import dev.anyjava.bot.stock.domain.Stock;
import dev.anyjava.bot.infra.stock.StockClient;
import dev.anyjava.bot.stock.domain.StockNoti;
import dev.anyjava.bot.stock.domain.StockNotiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class StockApiAdaptor implements StockQueryService {

    private final StockClient stockClient;
    private final StockNotiRepository stockNotiRepository;

    @Override
    public List<Stock> findAllByCodes(Collection<String> code) {
        return code.stream()
                .map(this::findByCode)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Stock> findByName(String name) {
        return stockNotiRepository.findAll().stream()
                .filter(v -> v.getName().equals(name))
                .findAny()
                .map(StockNoti::getCode)
                .map(this::findByCode);
    }

    Stock findByCode(String code) {
        String stock = stockClient.getStock(code);
        JSONObject json = XML.toJSONObject(stock);
        log.debug("read json={}", json);
        JSONObject stockInfo = json.getJSONObject("stockprice").getJSONObject("TBL_StockInfo");
        return Stock.builder()
                .code(code)
                .name(stockInfo.getString("JongName"))
                .currentPrice(new BigDecimal(stockInfo.getString("CurJuka").replaceAll(",", "")))
                .build();
    }
}
