package dev.anyjava.bot.adapter.stock;

import dev.anyjava.bot.stock.domain.StockNoti;
import dev.anyjava.bot.stock.domain.StockNotiRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class StockNotiInMemoryRepository implements StockNotiRepository {

    private static final Set<StockNoti> IN_MEMORY_SET;

    static {
        IN_MEMORY_SET = Set.of(
                StockNoti.of("035720", "카카오")
        );
    }

    @Override
    public List<StockNoti> findAll() {
        return List.copyOf(IN_MEMORY_SET);
    }
}
