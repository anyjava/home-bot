package dev.anyjava.bot.infra.order;

import dev.anyjava.bot.order.domain.OrderForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "orderSheetClient", url = "https://sheets.googleapis.com")
public interface OrderSheetClient {

    @GetMapping("/v4/spreadsheets/{spreadsheetId}/values/{range}")
    OrderForm getSheets(@PathVariable("spreadsheetId") String spreadsheetId,
                               @PathVariable("range") String range,
                               @RequestParam("key") String apiKey);

}

