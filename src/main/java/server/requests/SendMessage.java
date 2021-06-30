package server.requests;

import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;

public class SendMessage extends Task{

    int receiverId;
    String text;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {

        return null;
    }
}
