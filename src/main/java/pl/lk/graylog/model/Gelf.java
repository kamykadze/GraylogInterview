package pl.lk.graylog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Gelf {

    @JsonProperty("version")
    private String version;
    @JsonProperty("host")
    private String host;
    @JsonProperty("short_message")
    private String shortMassage;
    @JsonUnwrapped(prefix = "_")
    private Message customFields;

    @SuppressWarnings("unused")
    private Gelf() {
    }

    public Gelf(final Message message) {
        this.version = "1.1";
        this.host = "localhost";
        this.shortMassage = "Entry from : " + message.getClientIP();
        this.customFields = message;
    }

    public String getVersion() {
        return version;
    }

    public String getHost() {
        return host;
    }

    public String getShortMassage() {
        return shortMassage;
    }

    public Message getCustomFields() {
        return customFields;
    }
}
