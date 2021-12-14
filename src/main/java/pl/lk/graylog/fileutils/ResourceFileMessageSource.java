package pl.lk.graylog.fileutils;

import org.springframework.stereotype.Component;
import pl.lk.graylog.fileutils.exception.FileMessageSourceException;

import java.io.InputStream;
import java.util.Scanner;

@Component
public class ResourceFileMessageSource implements MessageSource {

    private Scanner scanner;
    private boolean opened;

    @Override
    public void open(final String resourceFileLocation) {
        if(resourceFileLocation == null) {
            throw new FileMessageSourceException("No resource file location provided, cannot open ResourceFileMessageSource");
        }
        if(opened) {
            throw new FileMessageSourceException("ResourceFileMessageSource has been already opened");
        }
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(resourceFileLocation);
        if(resourceAsStream != null) {
            this.scanner = new Scanner(resourceAsStream);
            this.opened = true;
        } else {
            throw new FileMessageSourceException("Failed to open file " + resourceFileLocation);
        }
    }

    @Override
    public boolean hasNextMessage() {
        return opened && scanner.hasNextLine();
    }

    @Override
    public String getNextMessage() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
        this.opened = false;
    }
}
