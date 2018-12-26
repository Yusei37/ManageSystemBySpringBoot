package club.yusei37.managesystem.service;

import club.yusei37.managesystem.bean.User;

import java.util.List;

/**
 * Created by Yusei on 2018/12/25
 */
public interface UserService {

    List<User> listAllUser();

    void addUser(User user);

    void saveUser(User user);

    void deleteUser(String userId);

    User readUser(String userId);
}
