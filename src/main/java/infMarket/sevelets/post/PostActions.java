package infMarket.sevelets.post;

import infMarket.sevelets.Action;
import infMarket.sevelets.member.AttendanceAction;
import infMarket.sevelets.member.LoginAction;
import infMarket.sevelets.member.LogoutAction;
import infMarket.sevelets.member.RegisterAction;

import java.util.HashMap;

public class PostActions {
    private HashMap<String, Action> postActions;

    public PostActions() {
        postActions = new HashMap<>();
        postActions.put("write", new PostWriteAction());
        postActions.put("delete", new PostDeleteAction());
        postActions.put("update", new PostUpdateAction());
    }

    public Action getAction(String path){
        return postActions.get(path);
    }
}
