package dev.anyjava.bot.torrent.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@ToString
public class Magnet {

    private static final Map<ProgramType, Magnet> NULL_MAGNET_MAP;

    static {
        Magnet nullMagnetEnt = new Magnet();
        nullMagnetEnt.id = 0L;
        nullMagnetEnt.type = ProgramType.ENT;

        Magnet nullMagnetDrama = new Magnet();
        nullMagnetDrama.id = 0L;
        nullMagnetDrama.type = ProgramType.DRAMA;

        Magnet nullMagnetMovie = new Magnet();
        nullMagnetMovie.id = 0L;
        nullMagnetMovie.type = ProgramType.MOVIE;

        NULL_MAGNET_MAP = Map.of(
                ProgramType.ENT, nullMagnetEnt,
                ProgramType.DRAMA, nullMagnetDrama,
                ProgramType.MOVIE, nullMagnetMovie
        );
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProgramType type;

    private String title;

    private long wrId;

    @Setter
    private String value;

    private LocalDateTime createdAt;

    public static Magnet init(ProgramType type, String title, long wrId) {
        Magnet magnet = new Magnet();
        magnet.title = title;
        magnet.type = type;
        magnet.wrId = wrId;
        magnet.createdAt = LocalDateTime.now();
        return magnet;
    }

    public static Magnet nullOf(ProgramType type) {
        return Magnet.NULL_MAGNET_MAP.get(type);
    }
}
