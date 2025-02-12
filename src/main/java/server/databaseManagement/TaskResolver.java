package server.databaseManagement;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import server.TransferImage;
import server.requests.Task;
import server.requests.*;

import java.util.Locale;

public class TaskResolver {

    static Task resolveTask(String raw) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(raw, JsonObject.class);
        String task = jsonObject.get("task").getAsString();

        switch ( task ){
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
            case "newPost":
                return gson.fromJson(raw, NewPostTask.class);
            case "notification":
                return gson.fromJson(raw, NotificationTask.class);
            case "postView":
                return gson.fromJson(raw, PostViewTask.class);
            case "profileView":
                return gson.fromJson(raw, ProfileViewTask.class);
            case "search":
                return gson.fromJson(raw, SearchTask.class);
            case "signUpPart1":
                return gson.fromJson(raw, SignUpPart1Task.class);
            case "signUpPart2":
                return gson.fromJson(raw, SignUpPart2Task.class);
            case "timeline":
                return gson.fromJson(raw, TimelineTask.class);
            case "unBlock":
                return gson.fromJson(raw, UnBlockTask.class);
            case "unFollow":
                return gson.fromJson(raw, UnFollowTask.class);
            case "unLike":
                return gson.fromJson(raw, UnLikeTask.class);
            case "checkCode":
                return gson.fromJson(raw, CheckCodeTask.class);
            case "getProPic":
                return gson.fromJson(raw, GetProPicTask.class);
            case "changeProPic":
                return gson.fromJson(raw, ChangeProPicTask.class);
            case "changeBio":
                return gson.fromJson(raw, ChangeBioTask.class);
            case "changeUsername":
                return gson.fromJson(raw, ChangeUsernameTask.class);
            case "showCommentsList":
                return gson.fromJson(raw, ShowCommentsListTask.class);
            case "showComments":
                return gson.fromJson(raw, ShowCommentsTask.class);
            case "getFollowersList":
                return gson.fromJson(raw, GetFollowersListTask.class);
            case "getFollowingList":
                return gson.fromJson(raw, GetFollowingListTask.class);
            case "addConnection":
                return gson.fromJson(raw, AddConnectionTask.class);
            case "getMessagesId":
                return gson.fromJson(raw, GetMessagesIdTask.class);
            case "sendMessage":
                return gson.fromJson(raw, SendMessageTask.class);
            case "showMessages":
                return gson.fromJson(raw, ShowMessagesTask.class);
            default:
                throw new IllegalStateException("Unexpected value: " + task.toLowerCase(Locale.ROOT));
        }
    }
}
