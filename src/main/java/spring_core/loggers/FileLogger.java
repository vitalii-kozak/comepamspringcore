package spring_core.loggers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring_core.beans.Event;
import spring_core.exeptions.FileReadExeption;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class FileLogger implements EventLogger{
    private String fileName;
    File file;

    public FileLogger() {
    }
    @Autowired
    public FileLogger(@Value("log.txt") String fileName) {
        this.fileName = fileName;
}

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void logEvent(Event event) throws  IOException{
        FileUtils.writeStringToFile(file, event.toString(), true);
    }

    @PostConstruct
    public void init() throws IOException{
        this.file = new File(fileName);
        file.canWrite();
    }

}
