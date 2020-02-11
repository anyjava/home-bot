package dev.anyjava.bot.torrent.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

@RequiredArgsConstructor
public enum ProgramType {
    ENT("예능", Constants.COMMON_PREDICATE),
    DRAMA("드라마", Constants.COMMON_PREDICATE),
    MOVIE("영화", m -> true);

    private final String desc;

    @Getter
    private final Predicate<Magnet> filter;

    private static class Constants {
        public static final Predicate<Magnet> COMMON_PREDICATE = m -> m.getTitle().contains("720") && m.getTitle().contains("NEXT");
    }
}
