package spring_core.beans;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

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
