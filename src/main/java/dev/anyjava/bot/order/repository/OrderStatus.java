package dev.anyjava.bot.order.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {
    ORDERED("주문접수"),
    DEPOSIT_COMPLETE("입금확인"),
    STAND_BY_DELIVERY("배송준비"),
    SHIPPING("배송중"),
    DONE_SHIPPING("배송완료");

    private final String desc;
}
