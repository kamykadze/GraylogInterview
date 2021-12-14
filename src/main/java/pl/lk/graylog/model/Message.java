package pl.lk.graylog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

    @JsonProperty("ClientDeviceType")
    private String clientDeviceType;
    @JsonProperty("ClientIP")
    private String clientIP;
    @JsonProperty("ClientIPClass")
    private String clientIPClass;
    @JsonProperty("ClientStatus")
    private int clientStatus;
    @JsonProperty("ClientRequestBytes")
    private long clientRequestBytes;
    @JsonProperty("ClientRequestReferer")
    private String clientRequestReferer;
    @JsonProperty("ClientRequestURI")
    private String clientRequestURI;
    @JsonProperty("ClientRequestUserAgent")
    private String clientRequestUserAgent;
    @JsonProperty("ClientSrcPort")
    private int clientSrcPort;
    @JsonProperty("EdgeServerIP")
    private String edgeServerIP;
    @JsonProperty("EdgeStartTimestamp")
    private long edgeStartTimestamp;
    @JsonProperty("DestinationIP")
    private String destinationIP;
    @JsonProperty("OriginResponseBytes")
    private long originResponseBytes;
    @JsonProperty("OriginResponseTime")
    private long originResponseTime;

    public String getClientDeviceType() {
        return clientDeviceType;
    }

    public String getClientIP() {
        return clientIP;
    }

    public String getClientIPClass() {
        return clientIPClass;
    }

    public int getClientStatus() {
        return clientStatus;
    }

    public long getClientRequestBytes() {
        return clientRequestBytes;
    }

    public String getClientRequestReferer() {
        return clientRequestReferer;
    }

    public String getClientRequestURI() {
        return clientRequestURI;
    }

    public String getClientRequestUserAgent() {
        return clientRequestUserAgent;
    }

    public int getClientSrcPort() {
        return clientSrcPort;
    }

    public String getEdgeServerIP() {
        return edgeServerIP;
    }

    public long getEdgeStartTimestamp() {
        return edgeStartTimestamp;
    }

    public String getDestinationIP() {
        return destinationIP;
    }

    public long getOriginResponseBytes() {
        return originResponseBytes;
    }

    public long getOriginResponseTime() {
        return originResponseTime;
    }
}
