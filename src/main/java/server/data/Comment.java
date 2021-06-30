package server.data;

public class Comment {

    int commentId;
    String text;
    int likes;
    String username;

    public Comment(int commentId, String text, int likes, String username) {
        this.commentId = commentId;
        this.text = text;
        this.likes = likes;
        this.username = username;
    }

}
