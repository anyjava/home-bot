package dev.anyjava.bot.adapter.adapter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MagnetDTOTest {

    @Disabled
    @Test
    void getWrId_torrentWal() {
        MagnetDTO magnetDTO = MagnetDTO.builder()
                .url("/torrent_variety/902651.html")
                .build();

        assertThat(magnetDTO.getWrId()).isEqualTo(902651L);
    }

    @Test
    void getWrId_torrentMax() {
        MagnetDTO magnetDTO = MagnetDTO.builder()
                .url("/max/VARIETY/12408")
                .build();

        assertThat(magnetDTO.getWrId()).isEqualTo(12408L);
    }
}