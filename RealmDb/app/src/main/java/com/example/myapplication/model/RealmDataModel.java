package com.example.myapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmDataModel extends RealmObject {
    @PrimaryKey
    private long id;
    private String UserName;
//    private String User_Role;
//    private String User_Email;

    private String UserPosition;


    public RealmDataModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPosition() {
        return UserPosition;
    }

    public void setUserPosition(String userPosition) {
        UserPosition = userPosition;
    }

//    public String getUser_Role() {
//        return User_Role;
//    }
//
//    public void setUser_Role(String user_Role) {
//        User_Role = user_Role;
//    }
//
//    public String getUser_Email() {
//        return User_Email;
//    }
//
//    public void setUser_Email(String user_Email) {
//        User_Email = user_Email;
//    }
}

