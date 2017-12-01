package spring_core.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Scope("prototype")
public class Event {
    private int id;
    private String msg;

    @Value("#{new java.util.Date()}")
    private Date date;
    @Autowired
    @Qualifier("dateFormat")
    private DateFormat df;

    public Event() {
    }

    public Event(Date date, DateFormat df) {
        this.id = (int)(Math.random()*1_000_000);
        this.date = date;
        this.df = df;
       }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
