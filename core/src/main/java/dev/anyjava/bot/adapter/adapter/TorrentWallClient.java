package dev.anyjava.bot.adapter.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "torrentWal", url = "https://m.torrentwal.com")
public interface TorrentWallClient {

    @GetMapping("/{type}/torrent1.htm")
    String getList(@PathVariable("type") String type);

    @GetMapping("/torrent_variety/902651.html")
    String getDetail();

    @GetMapping("/bbs/magnet2.php")
    String getMagnet(@RequestParam("bo_table") String type,
                     @RequestParam("wr_id") long wrId,
                     @RequestParam("no") String no,
                     @RequestParam("show") String show);
}
