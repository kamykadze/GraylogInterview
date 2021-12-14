package pl.lk.graylog.fileutils;

public interface MessageSource extends AutoCloseable {

    void open(String fileLocation);

    boolean hasNextMessage();

    String getNextMessage();

    void close();
}
