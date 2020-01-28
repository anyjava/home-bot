package dev.anyjava.bot.adapter.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
    DELIVERY_DATE("택배발송일자"),
    MEMO("주문시 요청 사항"),
    MEMO2("memo"),
    STATUS("상태"),
    DEPOSIT_TYPE("입금방법"),
    DELIVERY_COMPANY("택배사"),
    DELIVERY_INVOICE_NUMBER("송장번호")
    ;

    private final String name;
}
