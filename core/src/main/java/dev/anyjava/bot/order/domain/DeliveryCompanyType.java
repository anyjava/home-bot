package dev.anyjava.bot.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum DeliveryCompanyType {

    LOTTE("롯데", true, ""),
    LOGEN("로젠", true, "https://www.ilogen.com/web/personal/trace/"),
    POST_OFFICE("우체국", false, "https://service.epost.go.kr/trace.RetrieveDomRigiTraceList.comm?sid1="),
    NONE("그외", false, "");

    public final static Map<String, DeliveryCompanyType> MAP_BY_KOREAN_NAME;

    static {
        MAP_BY_KOREAN_NAME = Stream.of(DeliveryCompanyType.values())
                .collect(Collectors.toUnmodifiableMap(DeliveryCompanyType::getKoreanName, v -> v));
    }

    private final String koreanName;
    private final boolean ableTracking;
    private final String url;
}
