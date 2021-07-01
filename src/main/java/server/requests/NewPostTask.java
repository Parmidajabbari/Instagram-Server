package server.requests;

import server.SSSocket;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.Date;
import java.util.Base64;

public class NewPostTask extends server.requests.Task {

    //byte[] image;
    String caption;
    //String ownerName;


    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            //image = transferImage.getInput();
            SSSocket socket = transferImage.getSsSocket();
            byte[] image = socket.readMessage();
            String ownerName = databaseOps.idToUsername(currentUserId);
            //byte[] binaryImage = Base64.getDecoder().decode(image);
            Blob blob = new SerialBlob(image);
            Date date = new Date(System.currentTimeMillis());
            int postId = databaseOps.addNewPost(blob, date, currentUserId, caption, ownerName);
            result = "{'task' : 'newPost', 'error' : false, 'Result' : " + postId +" }";
        }
        catch (Exception e){
            result = "{'task' : 'newPost', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
            e.printStackTrace();
        }
        return result;
    }

    // create a new post in database and send a respond

}
