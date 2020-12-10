package infMarket.models.mybatis.dto;

import java.sql.Timestamp;

public class SellPost {
    private int sell_post_id;
    private String member_id;
    private String item_name;
    private String item_description;
    private Timestamp write_datetime;
    private String member_name;
    private String status;
    private int price;
    private String image_url;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public Timestamp getWrite_datetime() {
        return write_datetime;
    }

    public void setWrite_datetime(Timestamp write_datetime) {
        this.write_datetime = write_datetime;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
