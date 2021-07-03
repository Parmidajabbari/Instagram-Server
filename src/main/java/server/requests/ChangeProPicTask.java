package server.requests;

import server.SSSocket;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class ChangeProPicTask extends Task{

    String newPhoto;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            SSSocket socket = transferImage.getSsSocket();
            byte[] image = socket.readMessage();
            Blob blob = new SerialBlob(image);
            databaseOps.changeProfilePicture(currentUserId, blob);
            result = "{'task' : 'changeProPic', 'error' : false, 'Result' : 'done'}";
        }
        catch (Exception e){
            result = "{'task' : 'changeProPic', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }
}
