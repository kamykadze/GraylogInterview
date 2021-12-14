package pl.lk.graylog.sender;

import pl.lk.graylog.model.Gelf;

public interface LogSender {

    void send(Gelf gelf);

}
