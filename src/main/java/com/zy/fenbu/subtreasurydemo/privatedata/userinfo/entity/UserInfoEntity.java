package com.zy.fenbu.subtreasurydemo.privatedata.userinfo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoEntity {

    private String id;

    private String userId;

    private String info;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
