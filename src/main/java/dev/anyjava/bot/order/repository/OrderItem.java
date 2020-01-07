package dev.anyjava.bot.order.repository;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class OrderItem {

    private OrderItemType type;
    private int quantity;

    @Builder
    public OrderItem(OrderItemType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public static List<OrderItem> from(List row, RowHeader rowHeader) {
        ArrayList<OrderItem> items = Lists.newArrayList();
        add(items, (String) row.get(rowHeader.getIndex(HeadName.ITEM1)), OrderItemType.NO_1);
        add(items, (String) row.get(rowHeader.getIndex(HeadName.ITEM2)), OrderItemType.NO_2);
        add(items, (String) row.get(rowHeader.getIndex(HeadName.ITEM3)), OrderItemType.NO_3);
        return items;
    }

    public String getItemName() {
        return this.type.getName();
    }

    public BigDecimal getPrice() {
        return this.type.getPrice().multiply(BigDecimal.valueOf(this.getQuantity()));
    }

    private static void add(ArrayList<OrderItem> items, String cnt1, OrderItemType type) {
        if (Strings.isEmpty(cnt1)) return;
        items.add(
                OrderItem.builder()
                        .type(type)
                        .quantity(Integer.parseInt(cnt1))
                        .build()
        );
    }
}

@RequiredArgsConstructor
@Getter
enum OrderItemType {
    NO_1("1. 절약형", BigDecimal.valueOf(20_000)),
    NO_2("2. 실속형", BigDecimal.valueOf(40_000)),
    NO_3("3. 선물포장", BigDecimal.valueOf(55_000));

    private final String name;
    private final BigDecimal price;
}
