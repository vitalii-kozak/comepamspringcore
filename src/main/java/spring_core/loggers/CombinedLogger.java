package spring_core.loggers;

import spring_core.beans.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CombinedLogger implements EventLogger {

    private List<EventLogger> loggers = new ArrayList<>();

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
