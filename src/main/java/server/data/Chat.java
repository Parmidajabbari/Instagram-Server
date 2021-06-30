package server.data;

import java.util.ArrayList;

public class Chat {

    String task;
    boolean error;
    String result;
    ArrayList<Message> messagesList;

    public Chat(String task, boolean error, String result, ArrayList<Message> messagesList) {
        this.task = task;
        this.error = error;
        this.result = result;
        this.messagesList = messagesList;
    }

}
