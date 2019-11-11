package dev.anyjava.bot.torrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

@Slf4j
@Service
public class FolderWatchService {

    private final String downloadPath;

    public FolderWatchService(@Value("${home.bot.downloadPath}") String path) {
        this.downloadPath = path;
    }

    public void watch() {
        log.info("stared watch folder = {}", downloadPath);
        try(WatchService watchService = FileSystems.getDefault().newWatchService()) {
            Path watchPath = Paths.get(downloadPath);

            Files.walkFileTree(watchPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
                    return FileVisitResult.CONTINUE;
                }
            });

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    log.info("event kind: {}, Feil affected: {}", event.kind(), event.context());
                }
            }
        } catch (IOException | InterruptedException e) {
            log.error("failed watch folders, message={}", e.getMessage(), e);
        }
    }
}
