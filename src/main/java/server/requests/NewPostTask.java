package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.Date;
import java.util.Base64;

public class NewPostTask extends server.requests.Task {

    String image;
    String caption;
    String ownerName;


    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            ownerName = databaseOps.idToUsername(currentUserId);
            byte[] binaryImage = Base64.getDecoder().decode(image);
            Blob blob = new SerialBlob(binaryImage);
            Date date = new Date(System.currentTimeMillis());
            int postId = databaseOps.addNewPost(blob, date, currentUserId, caption, ownerName);
            result = "{'task' : 'newPost', 'error' : false, 'Result' : " + postId +" }";
        }
        catch (Exception e){
            result = "{'task' : 'newPost', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
        }
        return result;
    }

    // create a new post in database and send a respond

}
