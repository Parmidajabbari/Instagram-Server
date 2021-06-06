package server;

import java.sql.*;


public class MySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private final String url = "jdbc:mysql://localhost/instadb?";
    private final String username = "root";
    private final String password = "MJ@database12";

    public MySQLAccess() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection(url,username,password);
            createTables();
            //connect = DriverManager
              //      .getConnection("jdbc:mysql://localhost/instadb?"
                //            + "user=root&password=MJ@database12");

        }
        catch (Exception exception){
            exception.printStackTrace();
        }

    }

    private void createTables() throws SQLException {
        statement = connect.createStatement();

        // Create Users table
        statement.execute( "CREATE TABLE IF NOT EXISTS Users (\n" +
                "  userid int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  username varchar(25) DEFAULT NULL,\n" +
                "  password varchar(25) DEFAULT NULL,\n" +
                "  firstname varchar(45) NOT NULL,\n" +
                "  lastname varchar(45) NOT NULL,\n" +
                "  created date DEFAULT NULL,\n" +
                "  bio varchar(255) DEFAULT NULL,\n" +
                "  email varchar(255) DEFAULT NULL,\n" +
                "  PRIMARY KEY (userid), \n" +
                "  CONSTRAINT user_info UNIQUE(username),\n" +
                "  CONSTRAINT email_info UNIQUE(email)\n" +
                ")");
        statement.execute( "CREATE TABLE IF NOT EXISTS Following (\n" +
                "    followingUserId INT, \n" +
                "    followedUserId INT, \n" +
                "    PRIMARY KEY (followingUserId, followedUserId ),\n" +
                "    INDEX followIndex ( followingUserId, followedUserId )" +
                ")" );
        statement.execute( "CREATE TABLE IF NOT EXISTS Posts (\n" +
                "  postId int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  image mediumblob NOT NULL,\n" +
                "  uploaded date NOT NULL,\n" +
                "  userId int(11) NOT NULL,\n" +
                "  caption varchar(255) DEFAULT NULL,\n" +
                "  likes int(11) NOT NULL,\n" +
                "  PRIMARY KEY ( postId )\n" +
                "  )");
        statement.execute( "  " );
        //statement.execute(" CREATE INDEX IF NOT EXISTS followIndex ON Following (followingUserID)");
        try {
            Date date;
            statement.execute("INSERT INTO Users " +
                    "VALUES ( 302, 'MJM322', 'dalam', 'mj', 'mashal', '2021-02-03', 'hiii', 'mjm31.dalam@gmail.com' ) ");
            /*statement.execute(" INSERT INTO Following " +
                    "VALUES ( 22, 45)");
            statement.execute(" INSERT INTO Following " +
                    "VALUES (20, 44)");
            statement.execute(" INSERT INTO Following " +
                    "VALUES (21, 45)");
            statement.execute(" INSERT INTO Following " +
                    "VALUES (21, 35)");*/
        }
        catch ( SQLDataException sqlException ) {

        }
    }


    public Connection getConnect(){

        return connect;

    }

    private void close() {
        try {

            if (connect != null) {
                connect.close();
            }
        }
        catch (Exception e) {

        }
    }

}
