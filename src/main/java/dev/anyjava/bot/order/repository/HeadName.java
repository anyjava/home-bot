package dev.anyjava.bot.order.repository;

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
    MEMO("주문시 요청 사항"),
    STATUS("상태")
    ;

    private final String name;
}
