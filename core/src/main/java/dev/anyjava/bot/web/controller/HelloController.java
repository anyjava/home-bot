package dev.anyjava.bot.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RefreshScope
@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloController {

    @Value("${feign.host.torrent}")
    private String value;

    private final RefreshComponent refreshComponent;


    @GetMapping("hello")
    public Map<String, Object> getHello() {
        return Map.of("status", value);
    }

    @GetMapping("/force-refresh")
    public void forceRefresh() {
        refreshComponent.forceRefresh();
    }

}

@Component
@RequiredArgsConstructor
class RefreshComponent {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Async
    public void forceRefresh() {
        applicationEventPublisher.publishEvent(new RefreshEvent(this, "RefreshEvent", "Refreshing scope"));
    }
}
