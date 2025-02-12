package server.databaseManagement;

import server.data.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseOps {

    private final Connection conn;

    public DatabaseOps (){
        MySQLAccess mySQLAccess = new MySQLAccess();
        conn = mySQLAccess.getConnect();
    }


    public void deleteCode() throws SQLException {

        String query = " INSERT INTO Users ( username , email ) VALUES ( 'salamuser' , 'salamemail' ) ";
        Statement statement = conn.createStatement();
        statement.execute(query);

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
        System.out.println("check likes table");
        String query2 = " UPDATE Posts SET likes = likes + 1 WHERE postId = ? ";
        PreparedStatement stat = conn.prepareStatement(query2);
        stat.setInt(1, postId);
        stat.execute();
        System.out.println("check Posts table");

    }

    public void unlikePost( int userId, int postId ) throws SQLException{
        String query = " DELETE FROM Likes WHERE postId = ? AND userId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, postId);
        statement.setInt(2, userId);
        statement.execute();
        String query2 = " UPDATE Posts SET likes = likes - 1 WHERE postId = ? ";
        PreparedStatement stat = conn.prepareStatement(query2);
        stat.setInt(1, postId);
        stat.execute();
    }

    public int addComment( int userId, int postId, String comment, String username ) throws SQLException{
        String q = " UPDATE Posts SET comments = comments + 1 WHERE postId = ? ";
        PreparedStatement stat = conn.prepareStatement(q);
        stat.setInt(1, postId);
        stat.execute();

        String query = " INSERT INTO Comments " +
                " ( userId, postId, text, likes, username ) " +
                " VALUES " +
                " ( ? , ? , ? , ? , ? )";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);
        statement.setInt(2, postId);
        statement.setString(3, comment);
        statement.setInt(4, 0);
        statement.setString(5, username);
        statement.execute();

        String getId = "SELECT LAST_INSERT_ID()";
        Statement s = conn.createStatement();
        ResultSet resultSet = s.executeQuery(getId);
        int commentId = 0;
        while (resultSet.next()){
            commentId = resultSet.getInt(1);
        }
        return commentId;

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
        String query = " SELECT * FROM Users WHERE username = ? ";
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
        String query = " SELECT * FROM Users WHERE email = ? ";
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
        String query = "SELECT * FROM Verification WHERE username = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        int userCode = -1;
        System.out.println(" current code :  " + code );
        while( resultSet.next() ){
            userCode = resultSet.getInt("code");
            System.out.println( "code in data base :  " + userCode);
        }
        return code == userCode;
    }

    public int addNewUser (String username, String email, String password, Date created )throws SQLException{
        String query = "INSERT INTO Users " +
                "(username, password, created, email, followersNumber, followingNumber)" +
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

    public String idToUsername( int userId ) throws SQLException{
        String query = " SELECT Username FROM Users WHERE Id = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        String username = null;
        while(resultSet.next()){
            username = resultSet.getString("Username");
        }
        return username;
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

        String query2 = " UPDATE Users SET FollowingNumber = FollowingNumber + 1 WHERE Id = ? ";
        PreparedStatement stat = conn.prepareStatement(query2);
        stat.setInt(1, followerId);
        stat.execute();

        String q3 = " UPDATE Users SET FollowersNumber = FollowersNumber + 1 WHERE Id = ? ";
        PreparedStatement s3 = conn.prepareStatement(q3);
        s3.setInt(1, followedId);
        s3.execute();

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

        String query2 = " UPDATE Users SET FollowingNumber = FollowingNumber - 1 WHERE Id = ? ";
        PreparedStatement stat = conn.prepareStatement(query2);
        stat.setInt(1, follower);
        stat.execute();

        String q3 = " UPDATE Users SET FollowersNumber = FollowersNumber - 1 WHERE Id = ? ";
        PreparedStatement s3 = conn.prepareStatement(q3);
        s3.setInt(1, followed);
        s3.execute();

    }

    public int addNewPost( Blob img, Date uploaded, int ownerId, String caption, String ownerName ) throws SQLException{
        String query = " INSERT INTO Posts " +
                " ( image, uploaded, userId, ownerName, caption, likes, comments ) " +
                " VALUES " +
                " ( ? , ? , ? , ? , ? , ? , ?)";
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

    public Profile showProfile( int userId ) throws SQLException{
        String query = " SELECT * FROM Users WHERE Id = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        String userName = null;
        Blob proPic = null;
        String firstName = null;
        String lastName = null;
        Date created = null;
        String bio = null;
        int followersNumber = 0;
        int followingNumber = 0;
        boolean check = false;
        while (resultSet.next()){
            userName = resultSet.getString("username");
            firstName = resultSet.getString("firstname");
            lastName = resultSet.getString("lastname");
            created = resultSet.getDate("created");
            bio = resultSet.getString("bio");
            followersNumber = resultSet.getInt("followersNumber");
            followingNumber = resultSet.getInt("followingNumber");
            proPic = resultSet.getBlob("proPic");
            check = true;
        }
        if( check )
            return new Profile(userName, firstName, lastName, created, bio, followersNumber, followingNumber, proPic);
        return null;
    }

    public ArrayList<Integer> showUsersPosts ( int userId ) throws SQLException{
        String query = " SELECT postId FROM Posts WHERE userId = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Integer> posts = new ArrayList<>();
        while (resultSet.next()){
            posts.add( resultSet.getInt("postId") );
        }
        return posts;
    }

    public ArrayList<Integer> showUsersFollowings ( int userId ) throws SQLException{
        String query = " SELECT followedUserId FROM Following WHERE followingUserId = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Integer> followings = new ArrayList<>();
        while (resultSet.next()){
            followings.add( resultSet.getInt("followedUserId") );
        }
        return followings;
    }

    public void changeProfilePicture ( int userId, Blob img ) throws SQLException{
        String query = " UPDATE Users SET ProPic = ? WHERE Id = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setBlob(1, img);
        statement.setInt(2, userId);
        statement.execute();
    }

    public Blob getProfilePicture ( int userId )throws SQLException{
        String query = " SELECT ProPic FROM Users WHERE Id = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        Blob img = null;
        while (resultSet.next()){
            img = resultSet.getBlob("ProPic");
        }
        return img;
    }

    public void changeBio( int userId, String bio ) throws SQLException{
        String query = " UPDATE Users SET bio = ? WHERE Id = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, bio);
        statement.setInt(2, userId);
        statement.execute();
    }

    public void changeUsername( int userId, String username ) throws SQLException{
        String query = " UPDATE Users SET Username = ? WHERE Id = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        statement.setInt(2, userId);
        statement.execute();
    }

    public ArrayList<Integer> getCommentsList ( int postId ) throws SQLException{
        String query = " SELECT commentId FROM Comments WHERE postId = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, postId);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Integer> comments = new ArrayList<>();
        while (resultSet.next()){
            comments.add( resultSet.getInt("followedUserId") );
        }
        return comments;
    }

    public Comment showComment( int commentId ) throws SQLException{
        String query = " SELECT * FROM Comments WHERE commentId = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, commentId);
        ResultSet resultSet = statement.executeQuery();
        String text = null;
        int likes = 0;
        String username = null;
        while (resultSet.next()){
            text = resultSet.getString("text");
            likes = resultSet.getInt("likes");
            username = resultSet.getString("username");
        }
        return new Comment(commentId, text, likes, username);
    }

    public void addNewConnection( int currentUser, int secondUser, String currentUsername ,String secondUsername ) throws SQLException{
        String query = " INSERT INTO Connection VALUES ( ? , ? , ? ) ";

        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, currentUser);
        statement.setInt(2, secondUser);
        statement.setString(3, secondUsername);
        statement.execute();

        PreparedStatement stat = conn.prepareStatement(query);
        stat.setInt(1, secondUser);
        stat.setInt(2, currentUser);
        stat.setString(3, currentUsername);
        stat.execute();
    }

    public ArrayList<User> getConnectionsList (int userId ) throws SQLException{
        String query = " SELECT ( user2 , username2 ) FROM Connection WHERE user1 = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<User> connections = new ArrayList<>();
        while (resultSet.next()){
            int user2Id = resultSet.getInt("user2");
            String user2Name = resultSet.getString("username2");
            User user = new User(user2Id, user2Name);
            connections.add(user);
        }
        return connections;
    }

    public void sendMessage (int senderId, int receiverId, Date date, String text ) throws SQLException{
        String query = " INSERT INTO Direct ( sender, receiver, created, text ) VALUES ( ? , ? , ? , ? ) ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, senderId);
        statement.setInt(2, receiverId);
        statement.setDate(3, date);
        statement.setString(4, text);
        statement.execute();

    }

    public ArrayList<Integer> getChatId ( int senderId, int receiverId ) throws SQLException{
        String query = " SELECT messageId FROM Direct WHERE sender = ? AND receiver = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, senderId);
        statement.setInt(2, receiverId);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Integer> chatId = new ArrayList<>();
        while (resultSet.next()){
            chatId.add( resultSet.getInt("messageId") );
        }
        return chatId;
    }

    public Message getMessage(int messageId ) throws SQLException{
        String query = " SELECT * FROM Direct WHERE messageId = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, messageId);
        ResultSet resultSet = statement.executeQuery();
        int senderId = 0;
        int receiverId = 0;
        String text = null;
        Date date = null;
        while (resultSet.next()){
            senderId = resultSet.getInt("sender");
            receiverId = resultSet.getInt("receiver");
            text = resultSet.getString("text");
            date = resultSet.getDate("created");
        }
        return new Message(senderId, receiverId, text, date);
    }

    public ArrayList<User> getFollowingList ( int userId ) throws SQLException{
        String query = " SELECT followedUserId FROM Following WHERE followingUserId = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<User> followings = new ArrayList<>();
        while (resultSet.next()){
            int followingId = resultSet.getInt("followedUserId");
            String followingName = idToUsername(followingId);
            followings.add( new User(followingId, followingName) );
        }
        return followings;
    }

    public ArrayList<User> getFollowersList ( int userId ) throws SQLException{
        String query = " SELECT followingUserId FROM Following WHERE followedUserId = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<User> followers = new ArrayList<>();
        while (resultSet.next()){
            int followerId = resultSet.getInt("followingUserId");
            String followerName = idToUsername(followerId);
            followers.add( new User(followerId, followerName) );
        }
        return followers;
    }

}
