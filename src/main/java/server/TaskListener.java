package server;

import server.databaseManagement.TaskHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;

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
                TaskHandler taskHandler = new TaskHandler(message);
                taskHandler.doTask();
            }
            catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
