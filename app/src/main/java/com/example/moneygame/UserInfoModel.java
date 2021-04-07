package com.example.moneygame;

import java.util.Date;

public class UserInfoModel {
    private String first_name;
    private String last_name;
    private String user_email;
    private String registration_date;

    public UserInfoModel(String first_name, String last_name, String user_email, String registration_date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_email = user_email;
        this.registration_date = registration_date;
    }

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", user_email='" + user_email + '\'' +
                ", registration_date=" + registration_date +
                '}';
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }
}
