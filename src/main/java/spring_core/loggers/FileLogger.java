package spring_core.loggers;

import org.apache.commons.io.FileUtils;
import spring_core.beans.Event;
import spring_core.exeptions.FileReadExeption;

import java.io.File;
import java.io.IOException;

public class FileLogger implements EventLogger{
    private String fileName;
    File file;

    public FileLogger() {
    }

    public FileLogger(String fileName) {
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

    public void init() throws IOException{
        this.file = new File(fileName);
        file.canWrite();
    }

}
