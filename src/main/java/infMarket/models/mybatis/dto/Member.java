package infMarket.models.mybatis.dto;

import java.sql.Timestamp;

public class Member {
    private String member_id;
    private String password;
    private String name;
    private int gender;
    private Timestamp birth_date;
    private String email;
    private int point;
    private String authority;
    private Double seller_rating;
    private int level;
    private String phone_number;
    private Timestamp last_attendance_date;
    private int num_evaluated;



    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Timestamp getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Timestamp birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Double getSeller_rating() {
        return seller_rating;
    }

    public void setSeller_rating(Double seller_rating) {
        this.seller_rating = seller_rating;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public Timestamp getLast_attendance_date() {
        return last_attendance_date;
    }

    public void setLast_attendance_date(Timestamp last_attendance_date) {
        this.last_attendance_date = last_attendance_date;
    }

    public int getNum_evaluated() {
        return num_evaluated;
    }

    public void setNum_evaluated(int num_evaluated) {
        this.num_evaluated = num_evaluated;
    }
}
