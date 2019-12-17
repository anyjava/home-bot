package dev.anyjava.bot.stock.service;

import dev.anyjava.bot.infra.stock.Stock;

import java.util.Collection;
import java.util.List;

public interface StockQueryService {
    List<Stock> findAllByCodes(Collection<String> code);
}
