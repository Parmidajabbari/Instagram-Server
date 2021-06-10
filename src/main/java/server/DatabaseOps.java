package server;

import java.sql.*;

public class DatabaseOps {

    private Connection conn;
    private MySQLAccess mySQLAccess;

    public static void main( String[] args ){
        MySQLAccess mysql = new MySQLAccess();
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
        String query = " SELECT COUNT(*) FROM Likes WHERE postId = ? AND userId = ? ";
        //resultSet = statement.executeQuery( " SELECT COUNT(*) FROM Likes WHERE " );
        PreparedStatement stat = conn.prepareStatement(query);
        String pi = "" + postId;
        String ui = "" + userId;
        stat.setString(1, pi);
        stat.setString( 2, ui);
        resultSet = stat.executeQuery();
        resultSet.last();
        int like = resultSet.getRow();
        if ( like > 0 ){
            return true;
        }
        return false;
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
        String query = " SELECT COUNT(*) FROM Block WHERE blockerId = ? AND BlockedId = ? ";
        PreparedStatement stat = conn.prepareStatement(query);
        String br = "" + blockerId;
        String bd = "" + blockedId;
        stat.setString(1, br);
        stat.setString( 2, bd);
        resultSet = stat.executeQuery();
        resultSet.last();
        int block = resultSet.getRow();
        if ( block > 0 ){
            return true;
        }
        return false;
    }



}
