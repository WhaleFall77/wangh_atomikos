package com.zy.fenbu.subtreasurydemo.privatedata.userinfo.mapper;

import com.zy.fenbu.subtreasurydemo.privatedata.userinfo.entity.UserInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {

    void insert (UserInfoEntity userInfoEntity);
}
