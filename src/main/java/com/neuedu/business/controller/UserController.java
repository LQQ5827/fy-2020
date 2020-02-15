package com.neuedu.business.controller;

import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.dao.UserMapper;
import com.neuedu.business.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @类 名： UserController <br/>
 * @描 述： <br/>
 * @日 期： 2020/2/13 22:18<br/>
 * @作 者： 鼠小倩<br/>
 * @版 本： 1.0.0
 * @since JDK 1.8
 */
@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    /**
     * 访问id=1的用户信息
     * http://xxx:8888/user?id=1
     */
    @RequestMapping("/user")
    public User findUserById(@RequestParam("id") Integer userid){
        User user=new User();
        user.setId(userid);
        user.setUsername("admin1");
        //user.setUsername(username);
        return user;
    }

    /**
     * 测试mybatis
     *
     */
    /*@RequestMapping("/mabatis")
    public User findUser(){
        User user=userMapper.selectByPrimaryKey(76);
        return user;
    }*/


    /**
     * 测试是否返回高可用对象
     */
    @RequestMapping(value = "/mybatis/{userid}")
    @ResponseBody
    public ServerResponse findUser(@PathVariable("userid") int userid){
         User user=userMapper.selectByPrimaryKey(userid);
         if(user!=null){
             return ServerResponse.serverResponseBySuccess(null,user);
         }

         return ServerResponse.serverResponseByFail(1,"id不存在");
    }

}




