package pl.lk.graylog.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lk.graylog.fileutils.MessageSource;
import pl.lk.graylog.fileutils.exception.FileMessageSourceException;
import pl.lk.graylog.model.Gelf;
import pl.lk.graylog.model.Message;
import pl.lk.graylog.sender.LogSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogSendingAppTest {

    @InjectMocks
    private LogSendingApp toTest;

    @Mock
    private MessageSource messageSource;

    @Mock
    private LogSender logSender;

    @Mock
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private Message message;

    @BeforeEach
    public void setUp() throws Exception {
        lenient().doReturn(message).when(objectMapper).readValue(anyString(), eq(Message.class));
        lenient().doReturn("111.111.111.111").when(message).getClientIP();
    }

    @Test
    public void testSendsNoLogsIfNoMessages() {
        doReturn(false).when(messageSource).hasNextMessage();
        toTest.bombGraylogWithSampleData();
        verifyNoInteractions(logSender);
        verify(messageSource).close();
    }

    @Test
    public void testSendsProperNumberOfLogs() {
        when(messageSource.hasNextMessage())
                .thenReturn(true)
                .thenReturn(false);
        doReturn("\"ClientIP\": \"111.111.111.111\"").when(messageSource).getNextMessage();
        toTest.bombGraylogWithSampleData();
        verify(logSender).send(any(Gelf.class));
        verify(messageSource).close();
    }

    @Test
    public void testClosesMsgSourceEvenIfExceptionIsThrown() {
        lenient().doThrow(FileMessageSourceException.class).when(messageSource).open(anyString());
        verifyNoInteractions(logSender);
    }
}