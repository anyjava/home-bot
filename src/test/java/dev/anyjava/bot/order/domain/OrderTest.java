package dev.anyjava.bot.order.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void isShipping(OrderStatus orderStatus, boolean expected) {
        Order order = Order.builder()
                .status(orderStatus)
                .build();

        assertThat(order.isShipping()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(OrderStatus.ORDERED, false),
                Arguments.of(OrderStatus.DEPOSIT_COMPLETE, false),
                Arguments.of(OrderStatus.STAND_BY_DELIVERY, false),
                Arguments.of(OrderStatus.SHIPPING, true),
                Arguments.of(OrderStatus.DONE_SHIPPING, false)
        );
    }
}