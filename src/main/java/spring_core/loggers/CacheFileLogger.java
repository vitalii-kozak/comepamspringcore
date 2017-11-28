package spring_core.loggers;

import spring_core.beans.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileLogger extends FileLogger {
    private int cacheSize;
    List<Event> cache = new ArrayList<>();

    public CacheFileLogger(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        cache.add(event);
        if(cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}
