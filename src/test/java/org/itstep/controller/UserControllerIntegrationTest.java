package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;

import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class UserControllerIntegrationTest {

    @Autowired
    UserController controller;

    @Test
    public void all() {
        Iterable<User> users = controller.all();
        assertTrue("no users found", users.iterator().hasNext() == true);
    }

    @Test
    public void one() {
        Optional<User> user = controller.one(1);
        assertTrue("user not found", user.isPresent());
    }


}