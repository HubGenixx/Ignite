package Models;

public class MainResponseModelClass {
    private String requestId;
    private String message;
    private String type;

    public MainResponseModelClass(String requestId, String message, String type) {
        this.requestId = requestId;
        this.message = message;
        this.type = type;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
