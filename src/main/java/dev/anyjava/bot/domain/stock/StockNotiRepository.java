package dev.anyjava.bot.domain.stock;

import java.util.List;

public interface StockNotiRepository {
    List<StockNoti> findAll();
}
