package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.support.TestSupport;
import dev.anyjava.bot.torrent.domain.Magnet;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TorrentWalParserTest extends TestSupport {

    @Autowired
    private TorrentWallClient torrentWallClient;

    @Autowired
    private TorrentWalParser torrentWalParser;

    @Test
    void findAll() {
        String html = torrentWallClient.getList("torrent_variety");
        Document doc = Jsoup.parseBodyFragment(html);

        List<MagnetDTO> all = torrentWalParser.findAllList(doc);
        log.info(" >>> {}", all);

        assertAll(
                () -> assertThat(all.size()).isEqualTo(15),
                () -> assertThat(all.get(0).getTitle()).isNotEmpty(),
                () -> assertThat(all.get(0).getWrId()).isNotNull(),
                () -> assertThat(all.get(0).getUrl()).isNotEmpty()
        );
    }

    @Test
    void parseDetail() {
        String response = "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
                "<meta name = \"robots\" content=\"noindex,nofollow\">\n" +
                "<meta name=\"googlebot\" content=\"noindex, noarchieve\">\n" +
                "<script type=\"text/javascript\">\n" +
                "<!--\n" +
                "\tlocation.replace('magnet:?xt=urn:btih:5A99B7C85BFE7FA3265DEA6439BD7F02234D3571&dn=구해줘 홈즈.E42.200126.720p-NEXT.mp4&tr=http://open.acgnxtracker.com:80/announce&tr=http://tracker.mg64.net:6881/announce&tr=http://tracker.tvunderground.org.ru:3218/announce&tr=udp://182.176.139.129:6969/announce&tr=udp://9.rarbg.me:2710/announce&tr=udp://9.rarbg.me:2720/announce&tr=udp://9.rarbg.to:2710/announce&tr=udp://bt.xxx-tracker.com:2710/announce&tr=udp://eddie4.nl:6969/announce&tr=udp://exodus.desync.com:6969/announce&tr=udp://explodie.org:6969/announce&tr=udp://ipv4.tracker.harry.lu:80/announce&tr=udp://open.demonii.si:1337/announce&tr=udp://opentrackr.org:1337/announce&tr=udp://retracker.netbynet.ru:2710/announce&tr=udp://thetracker.org./announce&tr=udp://thetracker.org:80/announce&tr=udp://tracker.coppersurfer.tk:6969/announce&tr=udp://tracker.cyberia.is:6969/announce&tr=udp://tracker.internetwarriors.net:1337/announce&tr=udp://tracker.leechers-paradise.org:6969&tr=udp://tracker.leechers-paradise.org:6969/announce&tr=udp://tracker.open-internet.nl:6969/announce&tr=udp://tracker.openbittorrent.com:80/announce&tr=udp://tracker.opentrackr.org:1337/announce&tr=udp://tracker.tiny-vps.com:6969/announce&tr=udp://tracker.torrent.eu.org:451/announce&tr=udp://tracker.trackton.ga:7070/announce&tr=udp://www.eddie4.nl:6969/announce&tr=udp://zer0day.to:1337/announce&&tr=udp://tracker.openbittorrent.com:80/announce&tr=udp://tracker.opentrackr.org:1337/announce&tr=udp://public.popcorn-tracker.org:6969/announce&tr=udp://bt.xxx-tracker.com:2710/announce&tr=http://tracker.tfile.co:80/announce&tr=http://0d.kebhana.mx:443/announce&tr=http://retracker.spb.ru:80/announce&tr=http://peersteers.org:80/announce&tr=udp://kenserv.nerdpol.ovh:6969/announce&tr=udp://tracker.torrent.eu.org:451/announce&tr=https://open.acgnxtracker.com:443/announce&tr=udp://tracker.ds.is:6969/announce&tr=udp://exodus.desync.com:6969/announce&tr=udp://denis.stalker.upeer.me:6969/announce&tr=http://tracker.tfile.me:80/announce&tr=udp://tracker.justseed.it:1337/announce&tr=udp://tracker.port443.xyz:6969/announce&tr=udp://torr.ws:2710/announce&tr=udp://seedbay.net:2710/announce&tr=udp://tracker.0o.is:6969/announce&tr=udp://tracker.qt.is:6969/announce&tr=https://1.track.ga:443/announce&tr=udp://thetracker.org:80/announce&tr=udp://tracker.coppersurfer.tk:6969/announce&tr=udp://open.demonii.si:1337/announce&tr=udp://retracker.lanta-net.ru:2710/announce&tr=udp://tracker.tiny-vps.com:6969/announce&tr=udp://tracker.vanitycore.co:6969/announce&tr=udp://tracker.open-internet.nl:6969/announce&tr=udp://tracker.cypherpunks.ru:6969/announce&tr=udp://ipv4.tracker.harry.lu:80/announce&tr=http://share.camoe.cn:8080/announce&tr=udp://tracker.acg.gg:2710/announce&tr=udp://tracker.internetwarriors.net:1337/announce&tr=http://retracker.telecom.by:80/announce&tr=http://retracker.mgts.by:80/announce&tr=udp://tr.greenhat.be:80/announce&tr=http://open.acgnxtracker.com:80/announce&tr=http://tracker.mg64.net:6881/announce&tr=http://tracker.tvunderground.org.ru:3218/announce&tr=udp://182.176.139.129:6969/announce&tr=udp://9.rarbg.me:2710/announce&tr=udp://9.rarbg.me:2720/announce&tr=udp://9.rarbg.to:2710/announce&tr=udp://eddie4.nl:6969/announce&tr=udp://explodie.org:6969/announce&tr=udp://opentrackr.org:1337/announce&tr=udp://retracker.netbynet.ru:2710/announce&tr=udp://thetracker.org./announce&tr=udp://tracker.cyberia.is:6969/announce&tr=udp://tracker.leechers-paradise.org:6969&tr=udp://tracker.leechers-paradise.org:6969/announce&tr=udp://tracker.trackton.ga:7070/announce&tr=udp://www.eddie4.nl:6969/announce&tr=udp://zer0day.to:1337/announce');\n" +
                "//-->\n" +
                "</script>";

        Magnet magnet = new Magnet();
        torrentWalParser.parseDetail(magnet, response);

        assertThat(magnet.getValue()).isEqualTo("magnet:?xt=urn:btih:5A99B7C85BFE7FA3265DEA6439BD7F02234D3571");
    }

}