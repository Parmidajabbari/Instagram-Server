package server;

import com.google.gson.Gson;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.MySQLAccess;
import server.databaseManagement.TaskHandler;
import server.requests.FollowTask;
import server.requests.Task;

import javax.imageio.ImageIO;
import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class Test {

    public static void main(String[] args ) throws SQLException, IOException {
        //String task = "{ 'task' : 'signUpPart1', 'username' : 'mjuser1', 'email' : 'salam.g' }";
        //TaskHandler taskHandler = new TaskHandler(task);
        DatabaseOps databaseOps = new DatabaseOps();
        databaseOps.deleteCode();
        //System.out.println(taskHandler.doTask());
        /*String imgpath = "testimg.jpeg";
        BufferedImage img = ImageIO.read(new File(imgpath));
        FileInputStream fin = new FileInputStream(imgpath);
        System.out.println(fin.toString());
        MySQLAccess mySQLAccess = new MySQLAccess();
        Connection connection = mySQLAccess.getConnect();
        String query = " INSERT INTO Posts VALUES ( 1, ?, ?, 2, \"hello\", 4) ";
        PreparedStatement statement = connection.prepareStatement(query);
        Date date = new Date(System.currentTimeMillis());
        statement.setDate(2, date);
        statement.setBlob(1, fin);
        statement.execute();*/
    }
}
