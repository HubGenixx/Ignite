package Models;

public class RoutingObject {
    private String method;
    private String[] channels;

    public RoutingObject(String method, String[] channels) {
        this.method = method;
        this.channels = channels;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String[] getChannels() {
        return channels;
    }

    public void setChannels(String[] channels) {
        this.channels = channels;
    }
}
