package dev.anyjava.bot.torrent;

import dev.anyjava.bot.line.LineMessageBroker;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;

@Slf4j
public class FolderWatchServiceTest {

    FolderWatchService folderWatchService;

    LineMessageBroker lineMessageBroker = mock(LineMessageBroker.class);

    @Before
    public void setUp() {
        folderWatchService = new FolderWatchService("/var/tmp/TEMP", lineMessageBroker);
    }

    @Ignore // wait 가 걸리는 테스트
    @Test
    public void testWatchService() {
        folderWatchService.watch();
    }
}
