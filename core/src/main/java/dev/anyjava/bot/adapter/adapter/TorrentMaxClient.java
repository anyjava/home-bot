package dev.anyjava.bot.adapter.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "torrentMax", url = "https://torrentmax.co")
public interface TorrentMaxClient {

    @GetMapping("/max/{type}")
    String getList(@PathVariable("type") String type);

    @GetMapping("/max/{type}/{wr_id}")
    String getMagnet(@PathVariable("type") String type,
                     @PathVariable("wr_id") long wrId);
}
