package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.support.TestSupport;
import dev.anyjava.bot.torrent.domain.Magnet;
import dev.anyjava.bot.torrent.domain.ProgramType;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Ignore("api test 라 ignore")
class TorrentWalIndexerTest extends TestSupport {

    @Autowired
    private TorrentWalIndexer torrentWalIndexer;

    @Test
    void findMagnetUrl() {
        Magnet magnet = MagnetDTO.builder()
                .url("/torrent_variety/902651.html")
                .build()
                .toMagnet(ProgramType.ENT);

        Magnet magnetUrl = torrentWalIndexer.findMagnetUrl(magnet);

        assertThat(magnetUrl.getValue()).isNotEmpty();
    }
}