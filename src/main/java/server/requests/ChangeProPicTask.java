package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class ChangeProPicTask extends Task{

    String image;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            byte[] binaryImage = Base64.getDecoder().decode(image);
            Blob blob = new SerialBlob(binaryImage);
            databaseOps.changeProfilePicture(currentUserId, blob);
            result = "{'Task' : 'changeProPic', 'error' : false, 'Result' : 'done' }";
        }
        catch (Exception e){
            result = "{'Task' : 'changeProPic', 'error' : true, 'Result' : 'Something went wrong!}";
        }
        return result;
    }
}
