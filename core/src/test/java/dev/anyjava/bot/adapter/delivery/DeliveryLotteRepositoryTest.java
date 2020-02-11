package dev.anyjava.bot.adapter.delivery;

import com.google.common.collect.Maps;
import dev.anyjava.bot.support.TestSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class DeliveryLotteRepositoryTest extends TestSupport {

    @Autowired
    private DeliveryLotteRepository deliveryLotteRepository;

    @Test
    void getDeliveryInfo() {
        HashMap<String, String> param = Maps.newHashMap();
        param.put("InvNo", "101937981994");

        String deliveryInfo = deliveryLotteRepository.getDeliveryInfo(param);
        log.info("{}", deliveryInfo);

        assertThat(deliveryInfo).isNotEmpty();
    }
}