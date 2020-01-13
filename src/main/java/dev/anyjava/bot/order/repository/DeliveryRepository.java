package dev.anyjava.bot.order.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "deliveryClient", url = "https://ilogen.com")
public interface DeliveryRepository {

    @GetMapping(value = "/mobile/trace_r.asp")
    byte[] getDeliveryInfo(@RequestParam("gubun") String gubun,
                           @RequestParam("value1") String trackId);
}
