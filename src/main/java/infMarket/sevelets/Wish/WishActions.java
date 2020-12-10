package infMarket.sevelets.Wish;

import infMarket.sevelets.Action;

import java.util.HashMap;

public class WishActions {
    private HashMap<String, Action> postActions;

    public WishActions() {
        postActions = new HashMap<>();
        postActions.put("add", new WishAddAction());
    }

    public Action getAction(String path){
        return postActions.get(path);
    }
}
