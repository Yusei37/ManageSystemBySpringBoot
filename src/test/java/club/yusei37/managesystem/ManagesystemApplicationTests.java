package club.yusei37.managesystem;

import club.yusei37.managesystem.bean.User;
import club.yusei37.managesystem.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagesystemApplicationTests {

    @Resource
    private UserService userService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void addUserTest() {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setUserId("t" + i);
            user.setUsername("t" + i);
            user.setPassword("t" + i);
            user.setUserType("teacher");
            userService.addUser(user);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总计耗时: " + (endTime - beginTime) + " ms");
    }

    @Test
    public void deleteUserTest() {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            userService.deleteUser("t" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总计耗时: " + (endTime - beginTime) + " ms");
    }

    @Test
    public void readUserTest() {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            userService.readUser("t" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总计耗时: " + (endTime - beginTime) + " ms");
    }

    @Test
    public void readAfterWriteUserTest() {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setUserId("t" + i);
            user.setUsername("t" + i);
            user.setPassword("t" + i);
            user.setUserType("teacher");
            userService.addUser(user);
        }
        for (int i = 0; i < 10000; i++) {
            userService.readUser("t" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总计耗时: " + (endTime - beginTime) + " ms");
    }

    @Test
    public void cacheTest() {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            userService.readUser("admin");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总计耗时: " + (endTime - beginTime) + " ms");
    }

}

