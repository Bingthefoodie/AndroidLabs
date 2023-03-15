package Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {
    @ColumnInfo(name="message")
    protected String message;

    @ColumnInfo(name="TimeSent")
    protected String timeSent;

    @ColumnInfo(name="SendOrReceive")
    protected Boolean isSentButton;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    public int id;

    public ChatMessage (){}

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public Boolean getIsSentButton() {
        return isSentButton;
    }

    public void setMessage(String message) {
         this.message=message;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent= timeSent;
    }

    public void setIsSentButton(boolean isSentButton) {
        this.isSentButton= isSentButton;
    }


    public ChatMessage(String m, String t, boolean sent){
        message=m;
        timeSent=t;
        isSentButton=sent;
    }
}
