package dev.anyjava.bot.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DepositType {
    KAKAO("카카오 페이"),
    TOSS("토스"),
    NH("농협 계좌이체");

    private final String desc;
}
