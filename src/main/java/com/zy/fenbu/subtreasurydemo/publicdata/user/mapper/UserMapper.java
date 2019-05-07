package com.zy.fenbu.subtreasurydemo.publicdata.user.mapper;

import com.zy.fenbu.subtreasurydemo.publicdata.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper  {

    void insert(UserEntity userEntity);

    List<UserEntity> queryAll(UserEntity userEntity);
}
