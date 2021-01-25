package dev.anyjava.bot.order.domain;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void testGetTotalQuantity() {
        // given
        OrderItem item1 = OrderItem.builder().quantity(1).build();
        OrderItem item2 = OrderItem.builder().quantity(2).build();

        Order order = Order.builder()
                .items(Lists.newArrayList(item1, item2))
                .build();

        // when
        int totalQuantity = order.getTotalQuantity();

        // then
        assertThat(totalQuantity).isEqualTo(3);
    }

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