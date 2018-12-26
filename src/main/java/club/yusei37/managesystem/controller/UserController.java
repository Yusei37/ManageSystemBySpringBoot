package club.yusei37.managesystem.controller;

import club.yusei37.managesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import club.yusei37.managesystem.bean.User;

import java.util.List;

/**
 * Created by Yusei on 2018/12/25
 */
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/user/{userid}",method=RequestMethod.GET)
    public User searchUser(@PathVariable("userid") String userid){
        System.out.println("id="+ userid);
        User user = userService.readUser(userid);
        System.out.println(user);
        return user;
    }

    @RequestMapping(value="/user",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
    public String addUser(@RequestBody User user){
        System.out.println(user);
        userService.addUser(user);
        return "ok";
    }

    @RequestMapping(value="/user",method=RequestMethod.GET)
    public List<User> listUser(){
        return userService.listAllUser();
    }

    @RequestMapping(value="/user/{id}",method=RequestMethod.PUT,produces={"application/json;charset=UTF-8"})
    public String modifyUser(@RequestBody User user){
        userService.saveUser(user);
        return "ok";
    }

    @RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
        return "ok";
    }
}
