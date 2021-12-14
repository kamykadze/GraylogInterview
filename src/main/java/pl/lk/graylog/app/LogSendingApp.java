package pl.lk.graylog.app;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lk.graylog.fileutils.MessageSource;
import pl.lk.graylog.fileutils.exception.FileMessageSourceException;
import pl.lk.graylog.model.Gelf;
import pl.lk.graylog.model.Message;
import pl.lk.graylog.sender.LogSender;
import pl.lk.graylog.sender.exception.LogSenderException;

@Component
public class LogSendingApp {

    private static final Logger logger = Logger.getLogger(LogSendingApp.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LogSender logSender;

    private ObjectMapper objectMapper = new ObjectMapper();
    private String fileLocation = "sample-messages.txt";

    public void bombGraylogWithSampleData() {
        try {
            messageSource.open(fileLocation);
            while (messageSource.hasNextMessage()) {
                final String messageLine = messageSource.getNextMessage();
                try {
                    final Message message = objectMapper.readValue(messageLine, Message.class);
                    final Gelf gelf = new Gelf(message);
                    logSender.send(gelf);
                } catch (LogSenderException fex) {
                    logger.error("Failed to send message to Graylog : " + messageLine, fex);
                } catch (JacksonException jex) {
                    logger.error("Error parsing message : " + messageLine, jex);
                }
            }
        } catch (FileMessageSourceException fex) {
            logger.error("Failed to read file message source : " + fileLocation, fex);
        } finally {
            messageSource.close();
        }
    }
}
