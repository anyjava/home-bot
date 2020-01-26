package dev.anyjava.bot.order.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryInvoiceTest {

    @ParameterizedTest
    @MethodSource("getCompanies")
    void getDeliveryCompanyType(String companyKoreanName, DeliveryCompanyType expectedValue) {
        DeliveryInvoice deliveryInvoice = new DeliveryInvoice(companyKoreanName, "");

        assertThat(deliveryInvoice.getDeliveryCompanyType()).isEqualTo(expectedValue);
    }

    static Stream<Arguments> getCompanies() {
        return Stream.of(
                Arguments.of("롯데", DeliveryCompanyType.LOTTE),
                Arguments.of("로젠", DeliveryCompanyType.LOGEN),
                Arguments.of("우체국", DeliveryCompanyType.POST_OFFICE)
        );
    }
}