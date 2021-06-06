package server;

import java.sql.Connection;

public class DatabaseOps {

    public static void main( String[] args ){
        MySQLAccess mysql = new MySQLAccess();
        Connection conn = mysql.getConnect();
        ManagerHolder managerHolder = new ManagerHolderImpl();
    }

    public void createComment(){

    }

}
