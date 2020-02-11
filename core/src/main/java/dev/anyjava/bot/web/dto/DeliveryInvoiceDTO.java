package dev.anyjava.bot.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.anyjava.bot.order.domain.DeliveryDest;
import dev.anyjava.bot.order.domain.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@ToString
public class DeliveryInvoiceDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deliveryStartDate;
    private String orderName;
    private DeliveryDest deliveryDest;
    private List<OrderItem> items;
    private String memo;
    private DeliveryInvoiceSmsDTO deliveryInvoiceSmsDTO;
}
