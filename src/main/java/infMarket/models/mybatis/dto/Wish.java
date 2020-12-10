package infMarket.models.mybatis.dto;

public class Wish {
    private int wish_id;
    private int sell_post_id;
    private String member_id;
    private String item_name;

    public int getWish_id() {
        return wish_id;
    }

    public void setWish_id(int wish_id) {
        this.wish_id = wish_id;
    }

    public int getSell_post_id() {
        return sell_post_id;
    }

    public void setSell_post_id(int sell_post_id) {
        this.sell_post_id = sell_post_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
}
