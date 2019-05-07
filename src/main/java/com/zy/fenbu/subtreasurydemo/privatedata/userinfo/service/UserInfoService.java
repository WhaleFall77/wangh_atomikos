package com.zy.fenbu.subtreasurydemo.privatedata.userinfo.service;

import com.zy.fenbu.subtreasurydemo.privatedata.userinfo.entity.UserInfoEntity;
import com.zy.fenbu.subtreasurydemo.privatedata.userinfo.mapper.UserInfoMapper;
import com.zy.fenbu.subtreasurydemo.publicdata.user.entity.UserEntity;
import com.zy.fenbu.subtreasurydemo.publicdata.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserMapper userMapper;

    public void insert(UserInfoEntity userInfoEntity) {
        userInfoMapper.insert(userInfoEntity);
    }


    @Transactional()
    public void test () throws Exception{



            UserEntity userEntity = new UserEntity();
            userEntity.setId(UUID.randomUUID().toString());
            userEntity.setName("geeyo");
            userEntity.setPhone("15150517890");
            userEntity.setCreateTime(new Date().toString());
            userEntity.setCreateBy("zy");
            userMapper.insert(userEntity);
            int i = 1/0;
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setId(UUID.randomUUID().toString());
            userInfoEntity.setUserId(userEntity.getId());
            userInfoMapper.insert(userInfoEntity);


    }

}
