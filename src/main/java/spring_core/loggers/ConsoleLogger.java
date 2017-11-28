package spring_core.loggers;

import spring_core.beans.Event;

public class ConsoleLogger implements EventLogger{



    public void logEvent(Event event) {
        System.out.println(event);
    }
}
