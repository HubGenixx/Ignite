package Models;

public class MessageObjectClass {
    private RoutingObject routing;
    private ContentObject content;
    private ToObject to;

    public MessageObjectClass(RoutingObject routing, ContentObject content, ToObject to) {
        this.routing = routing;
        this.content = content;
        this.to = to;
    }

    public RoutingObject getRouting() {
        return routing;
    }

    public void setRouting(RoutingObject routing) {
        this.routing = routing;
    }

    public ContentObject getContent() {
        return content;
    }

    public void setContent(ContentObject content) {
        this.content = content;
    }

    public ToObject getTo() {
        return to;
    }

    public void setTo(ToObject to) {
        this.to = to;
    }
}
