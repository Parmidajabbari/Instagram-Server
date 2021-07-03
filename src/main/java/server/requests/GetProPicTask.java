package server.requests;

import server.SSSocket;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class GetProPicTask extends Task{

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            SSSocket socket = transferImage.getSsSocket();
            Blob blob = databaseOps.getProfilePicture(currentUserId);
            int blobLength = (int) blob.length();
            byte[] binaryImg = blob.getBytes(1, blobLength);
            result = "{'task' : 'getProPic', 'error' : false, 'Result' : 'done'}";
            transferImage.getOutput().writeUTF(result);
            socket.sendMessage(binaryImg);
            result = "{'task' : 'ignore', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
        }
        catch (Exception e){
            result = "{'task' : 'getProPic', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;    }
}
