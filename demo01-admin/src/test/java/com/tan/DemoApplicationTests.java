package com.tan;

import com.tan.entity.UsersEntity;
import com.tan.mapper.UsersMapper;
import com.tan.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class DemoApplicationTests {
    @Autowired
    MD5Util md5Util;
    @Autowired
    private UsersMapper usersMapper;
    @Test
    public void Test(){
        UsersEntity usersEntity = usersMapper.selectById(1);
        System.out.println(usersEntity);
    }
}
