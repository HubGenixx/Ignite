package Models;

public class MainObjectClass {
    private MessageObjectClass message;

    public MainObjectClass(MessageObjectClass message) {
        this.message = message;
    }

    public MessageObjectClass getMessage() {
        return message;
    }

    public void setMessage(MessageObjectClass message) {
        this.message = message;
    }
}
