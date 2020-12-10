package infMarket.sevelets.member;

import infMarket.sevelets.Action;

import java.util.HashMap;

public class MemberActions {
    private HashMap<String, Action> memberActions;

    public MemberActions() {
        memberActions = new HashMap<>();
        memberActions.put("login", new LoginAction());
        memberActions.put("register", new RegisterAction());
        memberActions.put("logout", new LogoutAction());
        memberActions.put("attendance", new AttendanceAction());
    }

    public Action getAction(String path){
        return memberActions.get(path);
    }
}
