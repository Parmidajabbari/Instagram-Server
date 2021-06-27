package server;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.TaskHandler;
import server.requests.FollowTask;
import server.requests.Task;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args ) throws SQLException {
        String task = "{ 'task' : 'unfollow', 'currentUserId' : 18, 'unFollowedUserId' : 14 }";
        TaskHandler taskHandler = new TaskHandler(task);
        DatabaseOps databaseOps = new DatabaseOps();
        System.out.println(taskHandler.doTask());
    }
}
