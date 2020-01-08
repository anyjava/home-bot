package dev.anyjava.bot.order.repository;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class OrderFormMapper {

    private static final int HEADER_OFFSET = 1;

    public static List<Order> map(List<List> values) {
        RowHeader header = mapHeader(values.get(0));
        return values.stream()
                .skip(HEADER_OFFSET)
                .map(row -> mapOrder(row, header))
                .collect(Collectors.toList());
    }

    public static Order mapOrder(List row, RowHeader rowHeader) {
        return Order.builder()
                .name(getStringValue(row, rowHeader, HeadName.ORDER_NAME))
                .phoneNumber(getStringValue(row, rowHeader, HeadName.ORDER_PHONE_NUMBER))
                .items(OrderItem.from(row, rowHeader))
                .deliveryDest(DeliveryDest.from(row, rowHeader))
                .memo(getStringValue(row, rowHeader, HeadName.MEMO))
                .status(mapStatus(getStringValue(row, rowHeader, HeadName.STATUS)))
                .build();
    }

    private static String getStringValue(List row, RowHeader rowHeader, HeadName headName) {
        try {
            return (String) row.get(rowHeader.getIndex(headName));
        } catch(IndexOutOfBoundsException e) {
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

@RequiredArgsConstructor
@Getter
enum HeadName {
    ID("id"),
    ORDER_NAME("주문자명"),
    ORDER_PHONE_NUMBER("주문자 연락처"),
    ITEM1("1번 절약형 (20,000원)"),
    ITEM2("2번 실속형 (40,000원)"),
    ITEM3("3번 선물포장 (55,000원)"),
    TO_NAME("택배 받는분 성함"),
    TO_PHONE_NUMBER("택배 받는분 연락처"),
    TO_ADDRESS("택배 받으실 곳"),
    WANTED_DATE("택배 받고 싶은 날짜"),
    MEMO("주문시 요청 사항"),
    STATUS("상태")
    ;

    private final String name;
}

class RowHeader {
    private Map<HeadName, Integer> header = Maps.newHashMap();

    public void add(Pair<HeadName, Integer> pair) {
        this.header.put(pair.getFirst(), pair.getSecond());
    }

    public int getIndex(HeadName headerName) {
        return header.get(headerName);
    }
}
