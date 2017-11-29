package spring_core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring_core.beans.Client;
import spring_core.beans.Event;
import spring_core.beans.EventType;
import spring_core.loggers.CacheFileLogger;
import spring_core.loggers.ConsoleLogger;
import spring_core.loggers.EventLogger;
import spring_core.loggers.FileLogger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class App {
    private Client client;
    private EventLogger eventLogger;
    Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    public void logEvent(EventType type, String msg) throws IOException {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = eventLogger;
        }
        Event event = new Event(new Date(), new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        String message = msg.replaceAll(client.getId(),client.getFullName());
        event.setMsg(message);
        logger.logEvent(event);
    }

    public static void main(String[] args) {

        //ApplicationContext parent = new ClassPathXmlApplicationContext("spring.xml");
        //ApplicationContext child = new ClassPathXmlApplicationContext( "spring.xml", parent);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        try {
            app.logEvent(EventType.ERROR, "Some event for 1");
            app.logEvent(EventType.INFO,"Some event for 2");
            app.logEvent(EventType.ERROR,"Some event for 3");
            app.logEvent(EventType.ERROR,"Some event for 4");
            app.logEvent(null,"Some event for 5");
            app.logEvent(null,"Some event for 6");
            app.logEvent(null,"Some event for 7");
            app.logEvent(null,"Some event for 8");
            app.logEvent(EventType.INFO,"Some event for 9");
            app.logEvent(EventType.INFO,"Some event for 10");
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
