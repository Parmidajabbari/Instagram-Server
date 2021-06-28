package server.databaseManagement;

import server.data.Post;

import javax.sql.rowset.serial.SerialBlob;
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
        String query = " SELECT * FROM Likes WHERE postId = ? AND userId = ? ";
        PreparedStatement stat = conn.prepareStatement(query);
        stat.setInt(1, postId);
        stat.setInt( 2, userId);
        ResultSet resultSet = stat.executeQuery();
        int like = 0;
        while (resultSet.next()){
            like ++;
        }
        return like > 0;
    }

    public void likePost( int userId, int postId ) throws SQLException{
        String query = " INSERT INTO Likes VALUES ( ? , ? )";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, postId);
        statement.setInt(2, userId);
        statement.execute();
    }

    public void unlikePost( int userId, int postId ) throws SQLException{
        String query = " DELETE FROM Likes WHERE postId = ? AND userId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, postId);
        statement.setInt(2, userId);
        statement.execute();
    }

    public boolean isBlocked( int blockedId, int blockerId ) throws SQLException{
        String query = " SELECT * FROM Block WHERE blockerId = ? AND BlockedId = ? ";
        PreparedStatement stat = conn.prepareStatement(query);
        stat.setInt(1, blockerId);
        stat.setInt(2, blockedId);
        ResultSet resultSet = stat.executeQuery();
        int block = 0;
        while (resultSet.next()){
            block ++;
        }
        return block > 0;
    }

    public boolean doesUsernameAlreadyExist( String username ) throws SQLException{
        String query = " SELECT COUNT(*) FROM Users WHERE username = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,username);
        ResultSet resultSet = statement.executeQuery();
        int usersNumber = 0;
        while (resultSet.next()){
            usersNumber ++;
        }
        return usersNumber > 0;
    }

    public boolean doesEmailAlreadyExist( String email ) throws SQLException{
        String query = " SELECT COUNT(*) FROM Users WHERE email = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,email);
        ResultSet resultSet = statement.executeQuery();
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
        statement.setInt(2, code);
        statement.setString(3,username);
        statement.setInt(4, code);
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
        statement.setDate(3, created);
        statement.setString(4, email);
        statement.setInt(5, 0);
        statement.setInt(6, 0);
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

    public void blockUser( int blockerId, int blockedId )throws SQLException{
        String query = " INSERT INTO Block VALUES ( ? , ? ) ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, blockerId);
        statement.setInt(2, blockedId);
        statement.execute();
    }

    public void unBlockUser( int blockerId, int blockedId ) throws SQLException{
        String query = " DELETE FROM Block WHERE blockerId = ? AND blockedId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, blockerId);
        statement.setInt(2, blockedId);
        statement.execute();
    }

    public void followUser( int followerId, int followedId )throws SQLException{
        String query = " INSERT INTO Following VALUES ( ? , ? ) ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, followerId);
        statement.setInt(2, followedId);
        statement.execute();
    }

    public boolean isAFollowingB( int followerId, int followedId )throws SQLException{
        String query = "SELECT * FROM Following WHERE followingUserId = ? AND followedUserId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, followerId);
        statement.setInt(2, followedId);
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
        statement.setInt(1, follower);
        statement.setInt(2, followed);
        statement.execute();
    }

    public int addNewPost( Blob img, Date uploaded, int ownerId, String caption, String ownerName ) throws SQLException{
        String query = " INSERT INTO Posts " +
                " ( img, uploaded, userId, ownerName, caption, likes, comments ) " +
                " VALUES " +
                " ( ? , ? , ? , ? , ? )";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setBlob(1, img);
        statement.setDate(2, uploaded);
        statement.setInt(3, ownerId);
        statement.setString(4, ownerName);
        statement.setString(5, caption);
        statement.setInt(6, 0);
        statement.setInt(7, 0);
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

    public Post getPost( int postId ) throws SQLException{
        String query = " SELECT * FROM Posts WHERE postId = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, postId);
        ResultSet resultSet = statement.executeQuery();
        Blob img = null;
        Date uploaded = null;
        int ownerId = 0;
        String ownerName = null;
        String caption = null;
        int likes = 0;
        int comments = 0;
        boolean check = false;
        while (resultSet.next()){
            img = resultSet.getBlob("image");
            uploaded = resultSet.getDate("uploaded");
            ownerId = resultSet.getInt("userId");
            ownerName = resultSet.getString("ownerName");
            caption = resultSet.getString("caption");
            likes = resultSet.getInt("likes");
            comments = resultSet.getInt("comments");
            check = true;
        }
        if(check)
            return new Post(img, caption, likes, comments, ownerName, uploaded, ownerId);
        return null;

    }

}
