package server.data;

import java.sql.Date;

public class Message {

    private int senderId;
    private int receiverId;
    private String text;
    private Date date;

    public Message(int senderId, int receiverId, String text, Date date) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.date = date;
    }

}
