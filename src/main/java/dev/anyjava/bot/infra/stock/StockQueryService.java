package dev.anyjava.bot.infra.stock;

import java.util.Collection;
import java.util.List;

public interface StockQueryService {
    List<Stock> findAllByCodes(Collection<String> code);
}
