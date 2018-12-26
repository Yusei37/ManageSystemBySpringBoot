package club.yusei37.managesystem.dao;

/**
 * Created by Yusei on 2018/12/25
 */
import java.util.List;

import club.yusei37.managesystem.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    @Select("SELECT * FROM user where usertype <> 'admin'")
    List<User> listAllUser();

    @Insert("insert into user(userid, password, username, usertype) values (#{userId}, #{password}, #{username}, " +
            "#{userType})")
    void addUser(User user);

    @Update("update user set username=#{username}, password=#{password}, usertype=#{userType} where userid=#{userId}")
    void saveUser(User user);

    @Delete("delete from user where userid=#{userId}")
    void deleteUser(String userId);

    @Select("SELECT * FROM user where userid =#{userId}")
    User readUser(String userId);
}