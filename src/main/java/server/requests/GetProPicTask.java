package server.requests;

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
            Blob blob = databaseOps.getProfilePicture(currentUserId);
            int blobLength = (int) blob.length();
            byte[] binaryImg = blob.getBytes(1, blobLength);
            String res = Base64.getEncoder().encodeToString(binaryImg);
            result = "{'Task' : 'getProPic', 'error' : false, 'Result' : '" + res +"' }";
        }
        catch (Exception e){
            result = "{'Task' : 'getProPic', 'error' : true, 'Result' : 'Something went wrong!}";
        }
        return result;    }
}
