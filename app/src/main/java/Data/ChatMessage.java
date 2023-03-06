package Data;

public class ChatMessage {
    String message;
    String timeSent;
    Boolean isSentButton;

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public Boolean getIsSentButton() {
        return isSentButton;
    }


    public ChatMessage(String m, String t, boolean sent){
        message=m;
        timeSent=t;
        isSentButton=sent;
    }
}
