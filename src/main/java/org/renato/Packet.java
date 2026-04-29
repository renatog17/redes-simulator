package org.renato;

public class Packet {

    private final String payload;
    private final String ipDestination;
    private final String ipSource;

    public Packet(String payload, String ipDestination, String ipSource) {
        this.payload = payload;
        this.ipDestination = ipDestination;
        this.ipSource = ipSource;
    }

    public String getPayload() {
        return payload;
    }

    public String getIpDestination() {
        return ipDestination;
    }

    public String getIpSource() {
        return ipSource;
    }
}