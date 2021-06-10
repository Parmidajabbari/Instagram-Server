package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TaskListener implements Runnable {

    private DataOutputStream output;
    private DataInputStream input;
    private boolean isOnline = true;

    public TaskListener(DataOutputStream output, DataInputStream input) {
        this.output = output;
        this.input = input;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    @Override
    public void run() {

        while (isOnline) {

            try {
                String message = input.readUTF();

                // doTask(message);

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
