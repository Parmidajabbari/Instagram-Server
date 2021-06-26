package server.databaseManagement;

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
                return gson.fromJson(raw, BlockTask.class);
            case "comment":
                return gson.fromJson(raw, CommentTask.class);
            case "direct":
                return gson.fromJson(raw, DirectTask.class);
            case "follow":
                return gson.fromJson(raw, FollowTask.class);
            case "like":
                return gson.fromJson(raw, LikeTask.class);
            case "login":
                return gson.fromJson(raw, LogInTask.class);
            case "newpost":
                return gson.fromJson(raw, NewPostTask.class);
            case "notification":
                return gson.fromJson(raw, NotificationTask.class);
            case "postview":
                return gson.fromJson(raw, PostViewTask.class);
            case "profileview":
                return gson.fromJson(raw, ProfileViewTask.class);
            case "search":
                return gson.fromJson(raw, SearchTask.class);
            case "signupPart1":
                return gson.fromJson(raw, SignUpPart1Task.class);
            case "signupPart2":
                return gson.fromJson(raw, SignUpPart2Task.class);
            case "timeline":
                return gson.fromJson(raw, TimelineTask.class);
            case "unblock":
                return gson.fromJson(raw, UnBlockTask.class);
            case "unfollow":
                return gson.fromJson(raw, UnFollowTask.class);
            case "unlike":
                return gson.fromJson(raw, UnLikeTask.class);
            case "checkCode":
                return gson.fromJson(raw, CheckCodeTask.class);
            default:
                throw new IllegalStateException("Unexpected value: " + task.toLowerCase(Locale.ROOT));
        }
    }
}