package server.databaseManagement;

import java.sql.*;


public class MySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private final String url = "jdbc:mysql://localhost/instadb?";
    private final String username = "root";
    private final String password = "MJ@database12";

    public MySQLAccess() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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

    public void createTables() throws SQLException {
        statement = connect.createStatement();

        // Create Users table
        statement.execute( "CREATE TABLE IF NOT EXISTS Users (\n" +
                "  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "  username varchar(25) DEFAULT NULL,\n" +
                "  password varchar(25) DEFAULT NULL,\n" +
                "  firstname varchar(45) DEFAULT NULL,\n" +
                "  lastname varchar(45) DEFAULT NULL,\n" +
                "  created date DEFAULT NULL,\n" +
                "  bio varchar(255) DEFAULT NULL,\n" +
                "  email varchar(255) DEFAULT NULL,\n" +
                "  followersNumber int(11) DEFAULT NULL,\n" +
                "  followingNumber int(11) DEFAULT NULL,\n" +
                "  proPic mediumblob DEFAULT NULL,\n" +
                //"  PRIMARY KEY (userid), \n" +
                "  CONSTRAINT user_info UNIQUE(username),\n" +
                "  CONSTRAINT email_info UNIQUE(email)" +
                ")");
        statement.execute( "ALTER TABLE Users AUTO_INCREMENT=100" );

        // Create following and followers table
        statement.execute( "CREATE TABLE IF NOT EXISTS Following (\n" +
                "    followingUserId INT, \n" +
                "    followedUserId INT, \n" +
                "    PRIMARY KEY (followingUserId, followedUserId ),\n" +
                "    INDEX followIndex ( followingUserId, followedUserId )" +
                ")" );

        //Create Block table
        statement.execute( "CREATE TABLE IF NOT EXISTS Block (\n" +
                "    blockerId int(11) NOT NULL, \n" +
                "    blockedId int(11) NOT NULL, \n" +
                "    PRIMARY KEY (blockerId, blockedId ),\n" +
                "    INDEX followIndex ( blockerId, blockedId )" +
                ")" );

        // Create Posts table
        statement.execute( "CREATE TABLE IF NOT EXISTS Posts (\n" +
                "  postId int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  image mediumblob NOT NULL,\n" +
                "  uploaded date NOT NULL,\n" +
                "  userId int(11) NOT NULL,\n" +
                "  ownerName varchar(25) DEFAULT NULL,\n" +
                "  caption varchar(255) DEFAULT NULL,\n" +
                "  likes int(11) NOT NULL,\n" +
                "  comments int(11) NOT NULL,\n" +
                "  PRIMARY KEY ( postId ),\n" +
                "  INDEX userPosts ( userId, postId )" +
                "  )");

        // Create Likes table
        statement.execute( " CREATE TABLE IF NOT EXISTS Likes ( \n" +
                " postId int(11) NOT NULL ,\n" +
                " userId int(11) NOT NULL ,\n" +
                " PRIMARY KEY ( postId, userId ), \n" +
                " INDEX likeIndex ( postId ) " +
                " ) " );

        // Create Comments table
        statement.execute( " CREATE TABLE IF NOT EXISTS Comments ( \n" +
                " commentId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, \n" +
                " userId int(11) NOT NULL, \n" +
                " postId int(11) NOT NULL, \n" +
                " text varchar(255) DEFAULT NULL, \n" +
                " likes int(11) NOT NULL, \n" +
                " username varchar(25) DEFAULT NULL,\n" +
                " INDEX postComments ( postId, commentId )" +
                " ) " );

        /* Create Replies table
        statement.execute( " CREATE TABLE IF NOT EXISTS Replies ( \n" +
                " replyId int(11) NOT NULL AUTO_INCREMENT, \n" +
                " commentId int(11) NOT NULL, \n" +
                " userId int(11) NOT NULL, \n" +
                " postId int(11) NOT NULL, \n" +
                " text varchar(255) DEFAULT NULL, \n" +
                " likes int(11) NOT NULL, \n" +
                " PRIMARY KEY ( replyId ), \n" +
                " INDEX postComments ( commentId, replyId )" +
                " ) " );*/

        // Create Verification code table
        statement.execute(" CREATE TABLE IF NOT EXISTS Verification ( \n " +
                "  username varchar(25) NOT NULL PRIMARY KEY,\n" +
                "  code int(5) NOT NULL" +
                "  )" );

        // Create Connection table
        statement.execute( " CREATE TABLE IF NOT EXISTS Connection (" +
                "  user1 int(11) NOT NULL, \n" +
                "  user2 int(11) NOT NULL, \n" +
                "  username2 varchar(25) DEFAULT NULL, \n" +
                "  PRIMARY KEY ( user1, user2 )" +
                "  )" );

        /* Create Direct table
        statement.execute( " CREATE TABLE IF NOT EXISTS Direct (" +
                "  messageId int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  sender int(11) NOT NULL, \n" +
                "  receiver int(11) NOT NULL, \n" +
                "  created date DEFAULT NULL, \n" +
                "  text varchar(255) DEFAULT NULL, \n" +
                "  INDEX message ( sender, receiver )" +
                "  )" );*/



        /*statement.execute( "  " );
        statement.execute(" CREATE INDEX IF NOT EXISTS followIndex ON Following (followingUserID)");
        try {
            Date date;
            statement.execute("INSERT INTO Users " +
                    "VALUES ( 302, 'MJM322', 'dalam', 'mj', 'mashal', '2021-02-03', 'hiii', 'mjm31.dalam@gmail.com' ) ");
            statement.execute(" INSERT INTO Following " +
                    "VALUES ( 22, 45)");
            statement.execute(" INSERT INTO Following " +
                    "VALUES (20, 44)");
            statement.execute(" INSERT INTO Following " +
                    "VALUES (21, 45)");
            statement.execute(" INSERT INTO Following " +
                    "VALUES (21, 35)");
        }
        catch ( SQLDataException sqlException ) {

        }*/
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
