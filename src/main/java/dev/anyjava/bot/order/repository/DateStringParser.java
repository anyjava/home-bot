package dev.anyjava.bot.order.repository;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateStringParser {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy. M. d");

    public static LocalDate parse(String dateString) {
        try {
            return LocalDate.parse(dateString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            log.error("failed to parseLocalDate, str={}", dateString, e);
            return LocalDate.MIN;
        }
    }
}
