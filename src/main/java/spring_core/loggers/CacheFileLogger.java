package spring_core.loggers;

import org.apache.commons.io.FileUtils;
import spring_core.beans.Event;
import spring_core.exeptions.FileReadExeption;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileLogger extends FileLogger {
    private int cacheSize;
    List<Event> cache = new ArrayList<>();

    public CacheFileLogger(String fileName, int cacheSize) {
        super(fileName);
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

    private void writeEventsFromCache() throws  IOException{
        for (Event event : cache
             ) {
            FileUtils.writeStringToFile(file, event.toString(), true);
        }

    }

    public void destroy() throws  IOException{
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

}
