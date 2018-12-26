package club.yusei37.managesystem.bean;

import java.io.Serializable;

/**
 * Created by Yusei on 2018/12/25
 */
public class User implements Serializable {
    private String userId;
    private String username;
    private String password;
    private String userType;

    public User() {
    }

    public User(String userId, String username, String password, String userType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    @Override
    public String toString() {
        return "user [userId=" + userId + ", username=" + username + ", password=" + password + ", userType=" + userType
                + "]";
    }
}
