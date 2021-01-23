package dev.anyjava.bot.adapter.order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateStringParser {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy. M. d");

    public static LocalDate parse(String dateString) {
        if (Strings.isEmpty(dateString)) {
            return LocalDate.now().plusDays(1);
        }

        try {
            return LocalDate.parse(dateString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            log.error("failed to parseLocalDate, str={}", dateString, e);
            return LocalDate.MIN;
        }
    }
}
