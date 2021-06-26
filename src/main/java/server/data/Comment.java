package server.data;

public class Comment {
    private String comment;
    private long userId;
    private long commentId;
    private static long idGenerate= 10^5;
    public Comment(String comment , long userId) {
        this.comment = comment;
        this.userId = userId;
        this.commentId = generateId();
    }
    public long generateId() {
        long temp = idGenerate;
        idGenerate++;
        return temp;
    }

    public long getCommentId() {
        return commentId;
    }

    public long getUserId() {
        return userId;
    }
}
