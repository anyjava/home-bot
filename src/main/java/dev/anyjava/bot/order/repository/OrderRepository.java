package dev.anyjava.bot.order.repository;

import java.util.List;

public interface OrderRepository {
    List<Order> findByPhoneNumber(String phoneNumber);
    List<Order> findAll();
    List<Order> findByOrderNumber(String orderName);
}
