package spring_core.loggers;

import org.apache.commons.io.FileUtils;
import spring_core.beans.Event;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger{
    private String fileName;
    File file;

    @Override
    public void logEvent(Event event) throws  IOException{
        FileUtils.writeStringToFile(file, event.toString(), true);
    }

    public void init() throws IOException {
        this.file = new File(fileName);
        file.canWrite();
    }
}
