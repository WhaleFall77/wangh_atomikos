package com.zy.fenbu.subtreasurydemo;

import com.zy.fenbu.subtreasurydemo.publicdata.user.entity.UserEntity;
import com.zy.fenbu.subtreasurydemo.publicdata.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubTreasuryDemoApplicationTests {

	@Autowired
	private UserService userService;
	@Test
	public void contextLoads() {
        List<UserEntity> userEntities = userService.queryAll(new UserEntity());

        System.out.println(userEntities);
    }

}
