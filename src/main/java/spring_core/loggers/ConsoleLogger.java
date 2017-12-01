package spring_core.loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring_core.beans.Event;

@Component
public class ConsoleLogger implements EventLogger{

    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
