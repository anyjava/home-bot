package dev.anyjava.bot.stock.domain;

import java.util.List;

public interface StockNotiRepository {
    List<StockNoti> findAll();
}
