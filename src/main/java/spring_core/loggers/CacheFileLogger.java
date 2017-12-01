package spring_core.loggers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring_core.beans.Event;
import spring_core.exeptions.FileReadExeption;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileLogger extends FileLogger {
    private int cacheSize;
    List<Event> cache = new ArrayList<>();

    @Autowired
    public CacheFileLogger(@Value("log.txt") String fileName,@Value("3") int cacheSize) {
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

    @PreDestroy
    public void destroy() throws  IOException{
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

}
