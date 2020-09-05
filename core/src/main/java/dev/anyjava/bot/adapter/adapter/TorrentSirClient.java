package dev.anyjava.bot.adapter.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "torrentSir", url = "${feign.host.torrent}")
public interface TorrentSirClient {

    @GetMapping("/bbs/board.php")
    String getList(@RequestParam("bo_table") String boTable);

    @GetMapping("/bbs/board.php")
    String getDetail(@RequestParam("bo_table") String boTable, @RequestParam("wr_id") long wrId);
}
