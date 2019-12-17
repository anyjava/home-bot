package dev.anyjava.bot.stock.service;

import dev.anyjava.bot.stock.domain.Stock;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StockQueryService {
    List<Stock> findAllByCodes(Collection<String> code);
    Optional<Stock> findByName(String name);
}
