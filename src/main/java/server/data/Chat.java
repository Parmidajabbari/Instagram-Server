package server.data;

import java.util.ArrayList;

public class Chat {

    String task;
    boolean error;
    String Result;
    ArrayList<Message> messagesList;

    public Chat(String task, boolean error, String result, ArrayList<Message> messagesList) {
        this.task = task;
        this.error = error;
        this.Result = result;
        this.messagesList = messagesList;
    }

}
