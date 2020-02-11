package dev.anyjava.bot.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public enum DeliveryStatus {

    NONE("ㅇdfasdlfnslkdf", "sdfsfdwefj"),
    READY("물품을 접수하였습니다.", "보내시는 고객님으로부터 상품을 인수했습니다."),
    DONE("고객님께 물품을 전달하였습니다.", "배달 완료하였습니다.");

    private final String parsingText;
    private final String parsingTextOfLotte;
}
