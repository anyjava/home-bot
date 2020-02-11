package dev.anyjava.bot.order.repository;

import dev.anyjava.bot.order.domain.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {
    List<Order> findByPhoneNumber(String phoneNumber);
    List<Order> findAll();
    List<Order> findByOrderNumber(String orderName);
    List<Order> findByDeliveryDate(LocalDate deliveryDate);
}
