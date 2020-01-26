package dev.anyjava.bot.adapter.delivery;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "deliveryLotteClient", url = "https://www.lotteglogis.com/mobile")
public interface DeliveryLotteRepository {

    @PostMapping(value = "/reservation/tracking/linkView", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String getDeliveryInfo(@RequestBody Map<String, ?> param);
}
