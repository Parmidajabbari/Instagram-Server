package server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import server.requests.Task;
import server.requests.*;

import java.util.Locale;

public class TaskResolver {

    static Task resolveTask(String raw) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(raw, JsonObject.class);
        String task = jsonObject.get("task").getAsString();

        switch ( task.toLowerCase(Locale.ROOT) ){
            case "block":
                BlockTask blockTask = gson.fromJson(raw, BlockTask.class);
                return blockTask;
            case "comment":
                CommentTask commentTask = gson.fromJson(raw, CommentTask.class);
                return commentTask;
            case "direct":
                DirectTask directTask = gson.fromJson(raw, DirectTask.class);
                return directTask;
            case "follow":
                FollowTask followTask = gson.fromJson(raw, FollowTask.class);
                return followTask;
            case "like":
                LikeTask likeTask = gson.fromJson(raw, LikeTask.class);
                return likeTask;
            case "login":
                LogInTask logInTask = gson.fromJson(raw, LogInTask.class);
                return logInTask;
            case "newpost":
                NewPostTask newPostTask = gson.fromJson(raw, NewPostTask.class);
                return newPostTask;
            case "notification":
                NotificationTask notificationTask = gson.fromJson(raw, NotificationTask.class);
                return notificationTask;
            case "postview":
                PostViewTask postViewTask = gson.fromJson(raw, PostViewTask.class);
                return postViewTask;
            case "profileview":
                ProfileViewTask profileViewTask = gson.fromJson(raw, ProfileViewTask.class);
                return profileViewTask;
            case "search":
                SearchTask searchTask = gson.fromJson(raw, SearchTask.class);
                return searchTask;
            case "signup":
                SignUpTask signUpTask = gson.fromJson(raw, SignUpTask.class);
                return signUpTask;
            case "timeline":
                TimelineTask timelineTask = gson.fromJson(raw, TimelineTask.class);
                return timelineTask;
            case "unblock":
                UnBlockTask unBlockTask = gson.fromJson(raw, UnBlockTask.class);
                return unBlockTask;
            case "unfollow":
                UnFollowTask unFollowTask = gson.fromJson(raw, UnFollowTask.class);
                return unFollowTask;
            case "unlike":
                UnLikeTask unLikeTask = gson.fromJson(raw, UnLikeTask.class);
                return unLikeTask;
            default:
                throw new IllegalStateException("Unexpected value: " + task.toLowerCase(Locale.ROOT));
        }
    }
}
