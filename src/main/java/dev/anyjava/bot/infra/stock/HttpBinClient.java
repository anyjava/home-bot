package dev.anyjava.bot.infra.stock;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "httpbin", url = "http://httpbin.org")
public interface HttpBinClient {

    @GetMapping("/delay/1")
    String getTimeout();

    @GetMapping("/status/500")
    String getError();
}
