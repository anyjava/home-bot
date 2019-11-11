package dev.anyjava.bot;

import dev.anyjava.bot.torrent.FolderWatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class HomeBotApplication implements CommandLineRunner {

    private final FolderWatchService folderWatchService;

    public static void main(String[] args) {
        SpringApplication.run(HomeBotApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("STARTED FOLDER WATCH!!");
        folderWatchService.watch();
        log.info("END FOLDER WATCH!!");
    }
}
