package dev.anyjava.bot.adapter.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class DateStringParserTest {

    @ParameterizedTest
    @CsvSource(value = {
            "2020. 1. 1   | 2020-01-01"
    }, delimiter = '|')
    void testDefaultParser(String dateStr, LocalDate expected) {
        // when
        LocalDate parsedDate = DateStringParser.parse(dateStr);

        // then
        assertThat(parsedDate).isEqualTo(expected);
    }

    @Test
    void testEmptyString() {
        // given
        String emptyString = "";
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        // when
        LocalDate parsedDate = DateStringParser.parse(emptyString);

        // then
        assertThat(parsedDate).isEqualTo(tomorrow);
    }
}