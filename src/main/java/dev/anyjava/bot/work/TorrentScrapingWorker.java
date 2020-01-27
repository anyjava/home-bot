package dev.anyjava.bot.work;

import dev.anyjava.bot.line.LineNotiService;
import dev.anyjava.bot.torrent.service.TorrentScrapingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TorrentScrapingWorker {

    private final TorrentScrapingService torrentScrapingService;
    private final LineNotiService lineNotiService;

    @Scheduled(cron = "0 */10 * * * *", zone = "JST")
    public void workerRun() {
        try {
            torrentScrapingService.run();
        } catch (RuntimeException e) {
            log.error("failed, magnet scraping job", e);
            lineNotiService.sendToAdmin("마그넷 스크래핑 job 이 실패했습니다. " + e.getMessage());
        }
    }
 }
