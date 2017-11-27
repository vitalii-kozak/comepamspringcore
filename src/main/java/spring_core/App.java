package spring_core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring_core.beans.Client;
import spring_core.loggers.ConsoleEventLogger;

public class App {
    private Client client;
    private ConsoleEventLogger eventLogger;

    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(String msg){
        String meggage = msg.replaceAll(client.getId(),client.getFullName());
        eventLogger.logEvent(meggage);
    }

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        app.logEvent("Some event for 1");
        app.logEvent("Some event for 2");
    }
}
