package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.controller.UserController;
import org.itstep.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class UserControllerRSQLIntegrationTest {

    @Autowired
    UserController controller;




}