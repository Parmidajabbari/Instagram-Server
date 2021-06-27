package server.databaseManagement;

import java.sql.*;

public class DatabaseOps {

    private final Connection conn;
    private final MySQLAccess mySQLAccess;

    public static void main( String[] args ) throws SQLException {
        MySQLAccess mysql = new MySQLAccess();
        mysql.createTables();
        Connection conn = mysql.getConnect();
        ManagerHolder managerHolder = new ManagerHolderImpl();
    }

    public DatabaseOps (){
        mySQLAccess = new MySQLAccess();
        conn = mySQLAccess.getConnect();
    }

    public void createComment(){

    }

    public boolean isAlreadyLiked( int userId, int postId ) throws SQLException {

        //Statement statement = conn.createStatement();
        ResultSet resultSet;
        String query = " SELECT * FROM Likes WHERE postId = ? AND userId = ? ";
        //resultSet = statement.executeQuery( " SELECT COUNT(*) FROM Likes WHERE " );
        PreparedStatement stat = conn.prepareStatement(query);
        String pi = "" + postId;
        String ui = "" + userId;
        stat.setString(1, pi);
        stat.setString( 2, ui);
        resultSet = stat.executeQuery();
        int like = 0;
        while (resultSet.next()){
            like ++;
        }
        return like > 0;
    }

    public boolean likePost( int userId, int postId ) throws SQLException{
        try {
            String query = " INSERT INTO Likes VALUES ( ? , ? )";
            PreparedStatement statement = conn.prepareStatement(query);
            String pi = "" + postId;
            String ui = "" + userId;
            statement.setString(1, pi);
            statement.setString(2, ui);
            statement.execute();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean isBlocked( int blockedId, int blockerId ) throws SQLException{
        ResultSet resultSet;
        String query = " SELECT * FROM Block WHERE blockerId = ? AND BlockedId = ? ";
        PreparedStatement stat = conn.prepareStatement(query);
        String br = "" + blockerId;
        String bd = "" + blockedId;
        stat.setString(1, br);
        stat.setString( 2, bd);
        resultSet = stat.executeQuery();
        //resultSet.last();
        int block = 0;
        while (resultSet.next()){
            block ++;
        }
        return block > 0;
    }

    public boolean doesUsernameAlreadyExist( String username ) throws SQLException{
        ResultSet resultSet;
        String query = " SELECT COUNT(*) FROM Users WHERE username = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,username);
        resultSet = statement.executeQuery();
        int usersNumber = 0;
        while (resultSet.next()){
            usersNumber ++;
        }
        return usersNumber > 0;
    }

    public boolean doesEmailAlreadyExist( String email ) throws SQLException{
        ResultSet resultSet;
        String query = " SELECT COUNT(*) FROM Users WHERE email = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,email);
        resultSet = statement.executeQuery();
        int emailsNumber = 0;
        while (resultSet.next()){
            emailsNumber ++;
        }
        return emailsNumber > 0;
    }

    public void addNewVCode(String username, int code )throws SQLException{
        String query = "INSERT INTO Verification VALUES ( ? , ? ) ON DUPLICATE KEY UPDATE " +
                "username = ? , code = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,username);
        statement.setString(2, String.valueOf(code));
        statement.setString(3,username);
        statement.setString(4, String.valueOf(code));
        statement.execute();
    }

    public boolean isCodeCorrect( String username, int code )throws SQLException{
        String query = "SELECT code FROM Verification WHERE username = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        int userCode = -1;
        while( resultSet.next() ){
            userCode = resultSet.getInt("code");
        }
        return code == userCode;
    }

    public int addNewUser (String username, String email, String password, Date created )throws SQLException{
        String query = "INSERT INTO Users " +
                "(Username, Password, Created, Email, FollowersNumber, FollowingNumber)" +
                "VALUES" +
                "( ? , ? , ? , ? , ? , ? )";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, created+"");
        statement.setString(4, email);
        statement.setString(5, 0 + "");
        statement.setString(6, 0 + "");
        statement.execute();
        String getId = "SELECT LAST_INSERT_ID()";
        Statement stat = conn.createStatement();
        ResultSet resultSet = stat.executeQuery(getId);
        int userId = 0;
        while (resultSet.next()){
            userId = resultSet.getInt(1);
        }
        return userId;
    }

    public int usernameToID( String username )throws SQLException{
        String query = "SELECT Id FROM Users WHERE Username = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        int userId = -1;
        while (resultSet.next() ){
            userId = resultSet.getInt(1);
        }
        return userId;
    }

    public int loginUser( String username, String password )throws SQLException{
        String query = " SELECT Id FROM Users WHERE Username = ? AND Password = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        int userId = -1;
        while (resultSet.next() ){
            userId = resultSet.getInt(1);
        }
        return userId;
    }

    public void followUser( int followerId, int followedId )throws SQLException{
        String query = " INSERT INTO Following VALUES ( ? , ? ) ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, String.valueOf(followerId));
        statement.setString(2, String.valueOf(followedId));
        statement.execute();
    }

    public boolean isAFollowingB( int followerId, int followedId )throws SQLException{
        String query = "SELECT * FROM Following WHERE followingUserId = ? AND followedUserId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, String.valueOf(followerId));
        statement.setString(2, String.valueOf(followedId));
        ResultSet resultSet = statement.executeQuery();
        int isFollowed = 0;
        while ( resultSet.next() ){
            isFollowed ++;
        }
        return isFollowed > 0;
    }

    public void unFollowUser( int follower, int followed ) throws SQLException{
        String query = " DELETE FROM Following WHERE followingUserId = ? AND followedUserId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, String.valueOf(follower));
        statement.setString(2, String.valueOf(followed));
        statement.execute();
    }

}
