package dev.anyjava.bot.order.repository;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderForm {
    private Long id;
    private String phoneNumber;

    public OrderForm(Long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }
}