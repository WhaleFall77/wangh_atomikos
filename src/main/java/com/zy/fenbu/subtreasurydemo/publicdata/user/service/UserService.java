package com.zy.fenbu.subtreasurydemo.publicdata.user.service;

import com.zy.fenbu.subtreasurydemo.publicdata.user.entity.UserEntity;
import com.zy.fenbu.subtreasurydemo.publicdata.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

   @Autowired
   private UserMapper userMapper;

    public void insert(UserEntity userEntity){
       userMapper.insert(userEntity);
   }

    public List<UserEntity> queryAll(UserEntity userEntity){
        return userMapper.queryAll(userEntity);
    }
}
