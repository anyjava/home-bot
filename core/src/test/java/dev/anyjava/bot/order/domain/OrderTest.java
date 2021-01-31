package dev.anyjava.bot.order.domain;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

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
    @CsvSource(value = {
            "로젠, 123-456, https://www.ilogen.com/web/personal/trace/123456",
            "우체국, 123-456, https://service.epost.go.kr/trace.RetrieveDomRigiTraceList.comm?sid1=123456",
            "롯데, 123-456, 123456",
            " , 123-456, ",
    })
    void testGetDeliveryTraceUrl(String company, String invoiceNumber, String expectedUrl) {
        // given
        Order order = Order.builder()
                .deliveryInvoice(DeliveryInvoice.builder()
                        .company(company == null ? "" : company)
                        .invoiceNumber(invoiceNumber)
                        .build())
                .build();

        // when
        String url = order.getDeliveryTraceUrl();

        // then
        assertThat(url).isEqualTo(expectedUrl == null ? "" : expectedUrl);
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