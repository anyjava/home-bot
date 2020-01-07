package dev.anyjava.bot.order.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class OrderForm {
    private String range;
    private List<Order> values;

    @JsonProperty("values")
    public void setValues(List<List> values) {
        this.values = OrderFormMapper.map(values);
    }
}
