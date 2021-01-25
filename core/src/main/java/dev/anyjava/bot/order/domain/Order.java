package dev.anyjava.bot.order.domain;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@ToString
public class Order {

    private Long rowId;
    private String name;
    private String phoneNumber;
    private List<OrderItem> items;
    private DeliveryDest deliveryDest;
    private String memo;
    private String memo2;
    private OrderStatus status;
    private DeliveryInvoice deliveryInvoice;
    private DepositType depositType;
    private BigDecimal discountAmount;

    @Builder
    public Order(Long rowId,
                 String name,
                 String phoneNumber,
                 List<OrderItem> items,
                 DeliveryDest deliveryDest,
                 String memo,
                 String memo2,
                 OrderStatus status,
                 DeliveryInvoice deliveryInvoice,
                 String depositType,
                 String discountAmount) {
        this.rowId = rowId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.items = items;
        this.deliveryDest = deliveryDest;
        this.memo = memo;
        this.memo2 = memo2;
        this.status = status;
        this.deliveryInvoice = deliveryInvoice;
        this.discountAmount = new BigDecimal(StringUtils.isEmpty(discountAmount) ? "0" : discountAmount);

        if (!StringUtils.isEmpty(depositType)) {
            this.depositType = DepositType.valueOf(depositType);
        }
    }

    public boolean acceptedOrder() {
        return Strings.isNotEmpty(phoneNumber);
    }

    public String getStatusStr() {
        return this.status.getDesc();
    }

    public BigDecimal getTotalPrice() {
        return this.items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isShipping() {
        return this.getStatus() == OrderStatus.SHIPPING;
    }

    public void doneShipping() {
        Preconditions.checkState(status == OrderStatus.SHIPPING, "Shipping 상태의 주문만 완료시킬수 있습니다. rowId={}", rowId);
        this.status = OrderStatus.DONE_SHIPPING;
    }

    public Optional<DepositType> getDepositType() {
        return Optional.ofNullable(this.depositType);
    }

    public LocalDate getDeliveryStartDate() {
        return this.deliveryDest.getDeliveryStartDate();
    }

    public int getTotalQuantity() {
        return this.items.stream()
                .map(OrderItem::getQuantity)
                .reduce(0, Integer::sum);
    }
}

