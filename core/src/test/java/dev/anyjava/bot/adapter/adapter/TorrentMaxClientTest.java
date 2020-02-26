package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.support.TestSupport;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Slf4j
class TorrentMaxClientTest extends TestSupport {

    @Autowired
    TorrentMaxClient client;

    @Test
    void getList() {
        log.info("RES => {}", client.getList("VARIETY"));
    }

    @Test
    void getMagnet() throws IOException {
//        client.getMagnet2("VARIETY", 12585, "1");

        OkHttpClient client = new OkHttpClient();
        String requestURL = "https://torrentmax.net/link?bo_table=VARIETY&wr_id=12585&no=1";
        Request request = new Request.Builder()
                .url(requestURL)
                .build(); //GET Request

        //동기 처리시 execute함수 사용
        Response response = client.newCall(request).execute();

        //출력
        log.info("{}", response.header("Location"));
    }
}