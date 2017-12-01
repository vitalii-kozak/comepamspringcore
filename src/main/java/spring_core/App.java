package spring_core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import spring_core.beans.Client;
import spring_core.beans.Event;
import spring_core.beans.EventType;
import spring_core.loggers.CacheFileLogger;
import spring_core.loggers.ConsoleLogger;
import spring_core.loggers.EventLogger;
import spring_core.loggers.FileLogger;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class App {

    @Autowired
    private Client client;

    @Autowired
    @Qualifier("cacheFileLogger")
    private EventLogger eventLogger;

    @Autowired
    @Qualifier("loggerMap")
    Map<EventType, EventLogger> loggers;
    private static ConfigurableApplicationContext ctx;

    public App() {
    }

    public App(Client client, @Qualifier("cacheFileLogger") EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    public void logEvent(EventType type, String msg) throws IOException {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = eventLogger;
        }
        Event event = (Event) ctx.getBean("event");

        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        logger.logEvent(event);
    }

    public static void main(String[] args) {

        //ApplicationContext parent = new ClassPathXmlApplicationContext("spring.xml");
        //ApplicationContext child = new ClassPathXmlApplicationContext( "spring.xml", parent);

        ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        try {
            app.logEvent(EventType.ERROR, "Some event for 1");
            TimeUnit.SECONDS.sleep(1);
            app.logEvent(EventType.INFO, "Some event for 2");
            TimeUnit.SECONDS.sleep(1);
            app.logEvent(EventType.ERROR, "Some event for 3");
            TimeUnit.SECONDS.sleep(1);
            app.logEvent(EventType.ERROR, "Some event for 4");
            TimeUnit.SECONDS.sleep(1);
            app.logEvent(null, "Some event for 5");
            TimeUnit.SECONDS.sleep(1);
            app.logEvent(null, "Some event for 6");
            TimeUnit.SECONDS.sleep(1);
            app.logEvent(null, "Some event for 7");
            TimeUnit.SECONDS.sleep(1);
            app.logEvent(null, "Some event for 8");
            TimeUnit.SECONDS.sleep(2);
            app.logEvent(EventType.INFO, "Some event for 9");
            TimeUnit.SECONDS.sleep(1);
            app.logEvent(EventType.INFO, "Some event for 10");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ctx.close();

    }
}
