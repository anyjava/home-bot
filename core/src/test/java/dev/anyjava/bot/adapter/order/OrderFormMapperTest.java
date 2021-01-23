package dev.anyjava.bot.adapter.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.anyjava.bot.order.domain.DepositType;
import dev.anyjava.bot.order.domain.Order;
import dev.anyjava.bot.support.TestSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderFormMapperTest extends TestSupport {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testOrderFormMapping() throws JsonProcessingException {

        List<Order> orders = objectMapper.readValue(json, OrderForm.class).getValues();
        Order aOrder = orders.get(0);

        assertAll(
                () -> assertThat(orders.size()).isEqualTo(8),
                () -> assertThat(aOrder.getRowId()).isEqualTo(1),
                () -> assertThat(aOrder.getName()).isEqualTo("아이유1").as("주문자 컬럼을 가져와야함"),
                () -> assertThat(aOrder.getPhoneNumber()).isEqualTo("010-1234-1234").as("연락처 컬럼을 가져와야함"),
                () -> assertThat(aOrder.getMemo()).isEqualTo("memoooo").as("주문시 요청 사항 컬럼을 가져와야함"),
                () -> assertThat(aOrder.getItems().size()).isEqualTo(1).as("item 갯수 검증"),
                () -> assertThat(aOrder.getDeliveryInvoice().getCompanyName()).isEqualTo("로젠"),
                () -> assertThat(aOrder.getDeliveryInvoice().getInvoiceNumber()).isEqualTo("729-2263-0001"),
                () -> assertThat(aOrder.getDepositType().get()).isEqualTo(DepositType.KAKAO),
                () -> assertThat(aOrder.getTotalPrice()).isEqualTo(BigDecimal.valueOf(16_0000L)),
                () -> assertThat(aOrder.getDiscountAmount()).isEqualTo(BigDecimal.valueOf(40_000L)),

                () -> assertThat(orders.get(1).getDeliveryInvoice().getCompanyName()).isEqualTo("")
        );
    }


    @MethodSource
    @ParameterizedTest
    void testOrderFormMapping2(String json) throws JsonProcessingException {
        List<Order> orders = objectMapper.readValue(json, OrderForm.class).getValues();
        Order aOrder = orders.get(0);

        assertAll(
                () -> assertThat(aOrder.getDeliveryDest().getDeliveryStartDate()).isEqualTo(LocalDate.now())
        );
    }

    static Stream<Arguments> testOrderFormMapping2() {
        return Stream.of(
            Arguments.of("{\n" +
                    "  \"range\": \"'설문지 응답 시트1'!A1:R9\",\n" +
                    "  \"majorDimension\": \"ROWS\",\n" +
                    "  \"values\": [\n" +
                    "    [\n" +
                    "      \"타임스탬프\",\n" +
                    "      \"주문자명\",\n" +
                    "      \"주문자 연락처\",\n" +
                    "      \"1번 절약형 (23,000원)\",\n" +
                    "      \"2번 실속형 (40,000원)\",\n" +
                    "      \"3번 선물포장 (50,000원)\",\n" +
                    "      \"택배 받으실 곳\",\n" +
                    "      \"택배 받는분 성함\",\n" +
                    "      \"택배 받는분 연락처\",\n" +
                    "      \"택배 받고 싶은 날짜\",\n" +
                    "      \"주문시 요청 사항\",\n" +
                    "      \"택배발송일자\",\n" +
                    "      \"상태\",\n" +
                    "      \"할인\",\n" +
                    "      \"입금방법\",\n" +
                    "      \"택배사\",\n" +
                    "      \"송장번호\",\n" +
                    "      \"memo\",\n" +
                    "      \"\",\n" +
                    "      \"\",\n" +
                    "      \"라인끝\\n\"\n" +
                    "    ],\n" +
                    "    [\n" +
                    "      \"2020. 1. 7 오후 5:38:00\",\n" +
                    "      \"아이유1\",\n" +
                    "      \"010-1234-1234\",\n" +
                    "      \"\",\n" +
                    "      \"4\",\n" +
                    "      \"\",\n" +
                    "      \"경기도 광주시 순암\",\n" +
                    "      \"아이유\",\n" +
                    "      \"아이유\",\n" +
                    "      \"1/21\",\n" +
                    "      \"memoooo\",\n" +
                    "      \"\",\n" +
                    "      \"DEPOSIT_COMPLETE\",\n" +
                    "      \"40000\",\n" +
                    "      \"KAKAO\",\n" +
                    "      \"로젠\",\n" +
                    "      \"729-2263-0001\",\n" +
                    "      \"\",\n" +
                    "      \"1\"\n" +
                    "    ]\n" +
                    "  ]\n" +
                    "}")
        );
    }

    //language=JSON
    String json = "{\n" +
            "  \"range\": \"'설문지 응답 시트1'!A1:R9\",\n" +
            "  \"majorDimension\": \"ROWS\",\n" +
            "  \"values\": [\n" +
            "    [\n" +
            "      \"타임스탬프\",\n" +
            "      \"주문자명\",\n" +
            "      \"주문자 연락처\",\n" +
            "      \"1번 절약형 (23,000원)\",\n" +
            "      \"2번 실속형 (40,000원)\",\n" +
            "      \"3번 선물포장 (50,000원)\",\n" +
            "      \"택배 받으실 곳\",\n" +
            "      \"택배 받는분 성함\",\n" +
            "      \"택배 받는분 연락처\",\n" +
            "      \"택배 받고 싶은 날짜\",\n" +
            "      \"주문시 요청 사항\",\n" +
            "      \"택배발송일자\",\n" +
            "      \"상태\",\n" +
            "      \"할인\",\n" +
            "      \"입금방법\",\n" +
            "      \"택배사\",\n" +
            "      \"송장번호\",\n" +
            "      \"memo\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"라인끝\\n\"\n" +
            "    ],\n" +
            "    [\n" +
            "      \"2020. 1. 7 오후 5:38:00\",\n" +
            "      \"아이유1\",\n" +
            "      \"010-1234-1234\",\n" +
            "      \"\",\n" +
            "      \"4\",\n" +
            "      \"\",\n" +
            "      \"경기도 광주시 순암\",\n" +
            "      \"아이유\",\n" +
            "      \"아이유\",\n" +
            "      \"1/21\",\n" +
            "      \"memoooo\",\n" +
            "      \"2020. 1. 8\",\n" +
            "      \"DEPOSIT_COMPLETE\",\n" +
            "      \"40000\",\n" +
            "      \"KAKAO\",\n" +
            "      \"로젠\",\n" +
            "      \"729-2263-0001\",\n" +
            "      \"\",\n" +
            "      \"1\"\n" +
            "    ],\n" +
            "    [\n" +
            "      \"2020. 1. 7 오후 5:42:34\",\n" +
            "      \"아이유\",\n" +
            "      \"010-123-1234\",\n" +
            "      \"\",\n" +
            "      \"3\",\n" +
            "      \"\",\n" +
            "      \"서울 성동구 독서당\",\n" +
            "      \"아이유\",\n" +
            "      \"010-1234-1234\",\n" +
            "      \"1/21\",\n" +
            "      \"\",\n" +
            "      \"2020. 1. 3\",\n" +
            "      \"DEPOSIT_COMPLETE\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"1\"\n" +
            "    ],\n" +
            "    [\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"3\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"1\"\n" +
            "    ],\n" +
            "    [\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"4\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"1\"\n" +
            "    ],\n" +
            "    [\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"5\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"1\"\n" +
            "    ],\n" +
            "    [\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"6\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"1\"\n" +
            "    ],\n" +
            "    [\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"7\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"1\"\n" +
            "    ],\n" +
            "    [\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"8\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"\",\n" +
            "      \"1\"\n" +
            "    ]\n" +
            "  ]\n" +
            "}";
}