package dev.anyjava.bot.order.repository;

import com.google.api.client.util.Lists;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderRepository {

    private static final String SPREADSHEET_ID = "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";
    private static final String RANGE = "Class Data!A2:E";

    private final Sheets sheets;

    public List<OrderForm> findByPhoeNumber(String phoneNumber) {
        List<List<Object>> values = getValueRange();
        if (values == null || values.isEmpty()) {
            log.debug("No Data found");
            return Lists.newArrayList();
        } else {
            return values.stream()
                    .map(v -> new OrderForm(0L, (String) v.get(0)))
                    .collect(Collectors.toList());
        }
    }

    private List<List<Object>> getValueRange() {
        try {
            return sheets.spreadsheets().values()
                        .get(SPREADSHEET_ID, RANGE)
                        .execute().getValues();
        } catch (IOException e) {
            log.error("failed to load sheets", e);
        }
        return Lists.newArrayList();
    }
}
