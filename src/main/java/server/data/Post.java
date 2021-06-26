package server.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Post implements Comparable<Post>{
    private String photoAddress;
    private String postId;
    private long userId;
    private String caption;
    private Date dateTime;
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Long> likedByUsers = new ArrayList<>();
    public Post(String photoAddress , String caption , long userId) {
        this.photoAddress = photoAddress;
        this.caption = caption;
        this.userId = userId;
        this.postId = generatePostId();
    }

    public String getPostId() {
        return postId;
    }
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date datetime) {
        this.dateTime = datetime;
    }

    public ArrayList<Long> getLikedByUsers() {
        return likedByUsers;
    }
    public long getLikesCount() {
        return likedByUsers.size();
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
    public long getCommentsCount() {
        return comments.size();
    }
    public String generatePostId() {
        return UUID.randomUUID().toString();
    }
    public static void addLike(Post post , long userId) {
        post.getLikedByUsers().add(userId);

    }
    public static boolean removeLike(Post post , long userId) {
        if(post.getLikedByUsers().contains(userId)) {
            post.getLikedByUsers().remove(userId);
            return true;
        }
        return false;

    }
    public static void addComment(Post post , String comment, long userId) {
        Comment comment1 = new Comment(comment , userId);
        post.getComments().add(comment1);

    }
    public static boolean removeComment(Post post , long commentId , long userId) {
        for(int i = 0 ; i < post.getComments().size() ; i++) {
            if (post.getComments().get(i).getCommentId() == commentId) {
                post.getComments().remove(i);
                return true;
            }
        }
    return false;
    }
    public static Post getPost(String postId , ArrayList<Post> posts) {
        for(int i = 0 ; i < posts.size() ; i++) {
            if(posts.get(i).getPostId().equals(postId)) {
                return posts.get(i);
            }
        }
        return null;
    }

    public long getUserId() {
        return userId;
    }

    public String getCaption() {
        return caption;
    }
    Date date = new Date();


    @Override
    public int compareTo(Post p) {
        return getDateTime().compareTo(p.getDateTime());
    }
}

