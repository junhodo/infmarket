package infMarket.models.mybatis.dto;

import java.sql.Timestamp;

public class Post {
    private int post_id;
    private String member_id;
    private String title;
    private String text;
    private String member_name;
    private Timestamp write_datetime;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public Timestamp getWrite_datetime() {
        return write_datetime;
    }

    public void setWrite_datetime(Timestamp write_datetime) {
        this.write_datetime = write_datetime;
    }
}
