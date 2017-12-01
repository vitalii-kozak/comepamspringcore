package spring_core.loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring_core.beans.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CombinedLogger implements EventLogger {

    @Autowired
    private List<EventLogger> loggers = new ArrayList<>();

    @Autowired
    public CombinedLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        for (EventLogger logger : loggers
             ) {
            logger.logEvent(event);
        }
    }
}
