package dev.anyjava.bot.order.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@Getter
@ToString
public class Order {

    private Long id;
    private String name;
    private String phoneNumber;
    private List<OrderItem> items;
    private DeliveryDest deliveryDest;
    private String memo;
    private OrderStatus status;

    @Builder
    public Order(Long id,
                 String name,
                 String phoneNumber,
                 List<OrderItem> items,
                 DeliveryDest deliveryDest,
                 String memo,
                 OrderStatus status) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.items = items;
        this.deliveryDest = deliveryDest;
        this.memo = memo;
        this.status = status;
    }

    public boolean acceptedOrder() {
        return Strings.isNotEmpty(phoneNumber);
    }
}

@RequiredArgsConstructor
enum OrderStatus {
    ORDERED("주문접수"),
    DEPOSIT_COMPLETE("입금확인"),
    STAND_BY_DELIVERY("배송준비"),
    SHIPPING("배송중"),
    DONE_SHIPPING("배송완료");

    private final String desc;
}
