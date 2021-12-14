package pl.lk.graylog.sender.exception;

public class LogSenderException extends RuntimeException {

    public LogSenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogSenderException(String message) {
        super(message);
    }
}
