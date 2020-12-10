package infMarket.sevelets.sellPost;

import infMarket.sevelets.Action;

import java.util.HashMap;

public class SellPostActions {
    private HashMap<String, Action> postActions;

    public SellPostActions() {
        postActions = new HashMap<>();
        postActions.put("write", new SellPostWriteAction());
        postActions.put("delete", new SellPostDeleteAction());
        postActions.put("modify", new SellPostUpdateAction());
        postActions.put("purchase", new PurchaseAction());
        postActions.put("eval", new EvalAction());
    }

    public Action getAction(String path){
        return postActions.get(path);
    }
}

