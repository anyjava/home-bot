package dev.anyjava.bot.adapter.order;

import com.google.common.collect.Maps;
import org.springframework.data.util.Pair;

import java.util.Map;

class RowHeader {
    private Map<HeadName, Integer> header = Maps.newHashMap();

    public void add(Pair<HeadName, Integer> pair) {
        this.header.put(pair.getFirst(), pair.getSecond());
    }

    public int getIndex(HeadName headerName) {
        return header.get(headerName);
    }
}