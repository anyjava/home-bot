package dev.anyjava.bot.order.service;

import dev.anyjava.bot.order.domain.DeliveryInvoice;
import dev.anyjava.bot.order.domain.DeliveryStatus;
import dev.anyjava.bot.order.domain.Order;

public interface DeliveryQueryService {
    Order findByTrackId(Order order);

    DeliveryStatus findByTrackId(DeliveryInvoice deliveryInvoice);
}
