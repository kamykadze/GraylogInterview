package pl.lk.graylog.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lk.graylog.model.Gelf;
import pl.lk.graylog.sender.exception.LogSenderException;

@Component
public class HttpLogSender implements LogSender {

    @Autowired
    private String graylogGelfURL;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void send(final Gelf gelf) {

        boolean receivedSuccessStatusCode = false;
        int statusCode = -1;
        try {
            String json = objectMapper.writeValueAsString(gelf);

            HttpPost post = new HttpPost(graylogGelfURL);
            post.setHeader("Content-Type","application/json");
            post.setEntity(new StringEntity(json));

            try (final CloseableHttpClient httpClient = HttpClients.createDefault();
                 final CloseableHttpResponse response = httpClient.execute(post)) {
                StatusLine statusLine = response.getStatusLine();
                if(statusLine != null) {
                    statusCode = statusLine.getStatusCode();
                    receivedSuccessStatusCode =  statusCode >= 200 && statusCode < 300;
                }
            }
        } catch (Exception e) {
            throw new LogSenderException("Failed to send log to Graylog" + e.getMessage(), e);
        }

        if(!receivedSuccessStatusCode) {
            throw new LogSenderException("Failed to send log to Graylog, HTTP status code : " + statusCode);
        }
    }
}
