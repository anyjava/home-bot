package dev.anyjava.bot.adapter.adapter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MagnetDTOTest {

    @Test
    void getWrId() {
        MagnetDTO magnetDTO = MagnetDTO.builder()
                .url("/torrent_variety/902651.html")
                .build();

        assertThat(magnetDTO.getWrId()).isEqualTo(902651L);
    }
}