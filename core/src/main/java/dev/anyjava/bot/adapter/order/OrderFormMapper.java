package dev.anyjava.bot.adapter.order;

import com.google.common.collect.Lists;
import dev.anyjava.bot.order.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Slf4j
public class OrderFormMapper {

    public static List<Order> map(List<List> values) {
        RowHeader header = mapHeader(values.get(0));
        List<Order> result = Lists.newArrayList();
        for (int i = 1; i < values.size(); ++i) {
            result.add(mapOrder(i, values.get(i), header));
        }
        return result;
    }

    public static Order mapOrder(long rowId, List row, RowHeader rowHeader) {
        return Order.builder()
                .rowId(rowId)
                .name(getStringValue(row, rowHeader, HeadName.ORDER_NAME))
                .phoneNumber(getStringValue(row, rowHeader, HeadName.ORDER_PHONE_NUMBER))
                .items(mapOrderItems(row, rowHeader))
                .deliveryDest(mapDeliveryDest(row, rowHeader))
                .memo(getStringValue(row, rowHeader, HeadName.MEMO))
                .memo2(getStringValue(row, rowHeader, HeadName.MEMO2))
                .status(mapStatus(getStringValue(row, rowHeader, HeadName.STATUS)))
                .deliveryInvoice(
                        DeliveryInvoice.builder()
                                .company(getStringValue(row, rowHeader, HeadName.DELIVERY_COMPANY))
                                .invoiceNumber(getStringValue(row, rowHeader, HeadName.DELIVERY_INVOICE_NUMBER))
                                .build()
                )
                .depositType(getStringValue(row, rowHeader, HeadName.DEPOSIT_TYPE))
                .discountAmount(getStringValue(row, rowHeader, HeadName.DISCOUNT))
                .build();
    }

    private static List<OrderItem> mapOrderItems(List row, RowHeader rowHeader) {
        List<OrderItem> items = Lists.newArrayList();
        add(items, getStringValue(row, rowHeader, HeadName.ITEM1), OrderItemType.NO_1);
        add(items, getStringValue(row, rowHeader, HeadName.ITEM2), OrderItemType.NO_2);
        add(items, getStringValue(row, rowHeader, HeadName.ITEM3), OrderItemType.NO_3);
        return items;
    }

    private static void add(List<OrderItem> items, String cnt1, OrderItemType type) {
        if (Strings.isEmpty(cnt1)) return;
        items.add(
                OrderItem.builder()
                        .type(type)
                        .quantity(Integer.parseInt(cnt1))
                        .build()
        );
    }

    private static DeliveryDest mapDeliveryDest(List row, RowHeader rowHeader) {
        return DeliveryDest.builder()
                .toName(getStringValue(row, rowHeader, HeadName.TO_NAME))
                .phoneNumber(makePhoneNumber(getStringValue(row, rowHeader, HeadName.TO_PHONE_NUMBER).replaceAll("-", "")))
                .address(getStringValue(row, rowHeader, HeadName.TO_ADDRESS))
                .wantedDate(getStringValue(row, rowHeader, HeadName.WANTED_DATE))
                .deliveryStartDate(DateStringParser.parse(getStringValue(row, rowHeader, HeadName.DELIVERY_DATE)))
                .build();
    }

    public static String makePhoneNumber(String phoneNoStr) {
        String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
        if (!Pattern.matches(regEx, phoneNoStr)) return "";
        return phoneNoStr.replaceAll(regEx, "$1-$2-$3");

    }

    private static String getStringValue(List row, RowHeader rowHeader, HeadName headName) {
        try {
            return (String) row.get(rowHeader.getIndex(headName));
        } catch (IndexOutOfBoundsException e) {
            log.error("failed to loadString type={}, index={}", headName, rowHeader.getIndex(headName));
            return Strings.EMPTY;
        }
    }

    private static RowHeader mapHeader(List list) {
        RowHeader header = new RowHeader();
        Stream.of(HeadName.values())
                .map(v -> Pair.of(v, list.lastIndexOf(v.getName())))
                .forEach(header::add);
        return header;
    }

    private static OrderStatus mapStatus(String s) {
        if (Strings.isEmpty(s)) return OrderStatus.ORDERED;
        return OrderStatus.valueOf(s);
    }

    private static Long parseLong(Object v) {
        String value = (String) v;
        if (Strings.isEmpty(value)) return null;
        else return Long.parseLong(value);
    }
}



