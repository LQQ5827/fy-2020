package com.neuedu.business.controller;

import com.neuedu.business.common.Consts;
import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.dao.UserMapper;
import com.neuedu.business.pojo.User;
import com.neuedu.business.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @类 名： UserController <br/>
 * @描 述： <br/>
 * @日 期： 2020/2/13 22:18<br/>
 * @作 者： 鼠小倩<br/>
 * @版 本： 1.0.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /*@Autowired
    UserMapper userMapper;*/
    @Autowired
    IUserService userService;

    /**
     * 注册
     */
    @RequestMapping("/register.do")
    public ServerResponse register(User user){
        return userService.registerLogin(user);
    }

    /**
     * 登录接口
     */
    @RequestMapping("login.do")
    public ServerResponse login(String username, String password, HttpSession session){
        ServerResponse response=userService.loginLogin(username, password);
        if(response.isSucess()){
            //登录成功
            session.setAttribute(Consts.USER,response.getData());
        }
        return response;
    }



    /**
     * 测试是否返回高可用对象
     */
   /* @RequestMapping(value = "/user/{userid}")
    @ResponseBody
    public ServerResponse findUser(@PathVariable("userid") int userid){
         User user=userMapper.selectByPrimaryKey(userid);
         if(user!=null){
             return ServerResponse.serverResponseBySuccess(null,user);
         }

         return ServerResponse.serverResponseByFail(1,"id不存在");
    }*/

}




