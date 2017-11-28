package spring_core.loggers;

import spring_core.beans.Event;

import java.io.IOException;

public interface EventLogger {
    public void logEvent(Event event) throws IOException;
}
