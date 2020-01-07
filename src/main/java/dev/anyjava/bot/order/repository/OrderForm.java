package dev.anyjava.bot.order.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class OrderForm {
    private String range;
    private List<Order> values;

    @JsonProperty("values")
    public void setValues(List<List> values) {
        this.values = values.stream()
                .map(this::buildOrder)
                .collect(Collectors.toList());
    }

    private Order buildOrder(List row) {
        return Order.builder()
                .id(parseLong(row.get(11)))
                .name((String) row.get(1))
                .phoneNumber((String) row.get(2))
                .items(OrderItem.from(row))
                .deliveryDest(DeliveryDest.from(row))
                .memo((String) row.get(10))
                .status(mapStatus((String) row.get(12)))
                .build();
    }

    private OrderStatus mapStatus(String s) {
        if (Strings.isEmpty(s)) return OrderStatus.ORDERED;
        return OrderStatus.valueOf(s);
    }

    private Long parseLong(Object v) {
        String value = (String) v;
        if (Strings.isEmpty(value)) return null;
        else return Long.parseLong(value);
    }
}
