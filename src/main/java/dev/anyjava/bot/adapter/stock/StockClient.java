package dev.anyjava.bot.adapter.stock;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stockClient", url = "http://asp1.krx.co.kr")
public interface StockClient {

    @GetMapping("/servlet/krx.asp.XMLSise")
    String getStock(@RequestParam("code") String code);
}
