package club.yusei37.managesystem.service.impl;

import club.yusei37.managesystem.bean.User;
import club.yusei37.managesystem.dao.UserDao;
import club.yusei37.managesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yusei on 2018/12/25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<User> listAllUser() {
        List<User> userList = userDao.listAllUser();
        return userList;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
        redisTemplate.opsForValue().set(user.getUserId(), user);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
        redisTemplate.opsForValue().getOperations().delete(user.getUserId());
        redisTemplate.opsForValue().set(user.getUserId(), user);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
        redisTemplate.opsForValue().getOperations().delete(userId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public User readUser(String userId) {
        User user = (User) redisTemplate.opsForValue().get(userId);
        if (user != null) {
            return user;
        }
        user = userDao.readUser(userId);
        return user;
    }
}
