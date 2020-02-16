package com.neuedu.business.service.impl;

import com.neuedu.business.common.RoleEnum;
import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.common.StatusEnum;
import com.neuedu.business.dao.UserMapper;
import com.neuedu.business.pojo.User;
import com.neuedu.business.service.IUserService;
import com.neuedu.business.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @类 名： UserServiceImpl <br/>
 * @描 述： <br/>
 * @日 期： 2020/2/15 21:51<br/>
 * @作 者： 鼠小倩<br/>
 * @版 本： 1.0.0
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ServerResponse registerLogin(User user) {
        //1.参数非空判断
        if(user==null){
            return ServerResponse.serverResponseByFail(StatusEnum.PARAM_NOT_EMPTY.getStatus(),StatusEnum.PARAM_NOT_EMPTY.getDesc());
        }
        //2.用户名非空判断
        if(user.getUsername()==null||user.getUsername().equals("")){
            return ServerResponse.serverResponseByFail(StatusEnum.USERNAME_NOT_EMPTY.getStatus(),StatusEnum.USERNAME_NOT_EMPTY.getDesc());
        }
        //3.密码非空判断
        if(user.getPassword()==null||user.getPassword().equals("")){
            return ServerResponse.serverResponseByFail(StatusEnum.PASSWORD_NOT_EMPTY.getStatus(),StatusEnum.PASSWORD_NOT_EMPTY.getDesc());
        }
        //4.邮箱非空判断
        if(user.getEmail()==null||user.getEmail().equals("")){
            return ServerResponse.serverResponseByFail(StatusEnum.EMAIL_NOT_EMPTY.getStatus(),StatusEnum.EMAIL_NOT_EMPTY.getDesc());
        }
        //5.联系方式非空判断
        if(user.getPhone()==null||user.getPhone().equals("")){
            return ServerResponse.serverResponseByFail(StatusEnum.PHONE_NOT_EMPTY.getStatus(),StatusEnum.PHONE_NOT_EMPTY.getDesc());
        }
        //6.密保问题非空判断
        if(user.getQuestion()==null||user.getQuestion().equals("")){
            return ServerResponse.serverResponseByFail(StatusEnum.QUESTION_NOT_EMPTY.getStatus(),StatusEnum.QUESTION_NOT_EMPTY.getDesc());
        }
        //7.密保答案非空判断
        if(user.getAnswer()==null||user.getAnswer().equals("")){
            return ServerResponse.serverResponseByFail(StatusEnum.ANSWER_NOT_EMPTY.getStatus(),StatusEnum.ANSWER_NOT_EMPTY.getDesc());
        }

        //判断用户名是否存在
        int count=userMapper.countUsername(user.getUsername());
        if(count>0){
            //用户名存在
            return ServerResponse.serverResponseByFail(StatusEnum.USERNAME_EXISTS.getStatus(),StatusEnum.USERNAME_EXISTS.getDesc());
        }

        //判断邮箱是否存在
        int countEmail=userMapper.countEmail(user.getEmail());
        if(countEmail>0){
            //用户名存在
            return ServerResponse.serverResponseByFail(StatusEnum.EMAIL_EXISTS.getStatus(),StatusEnum.EMAIL_EXISTS.getDesc());
        }

        //注册
        //对明文的密码践行加密
        String password=MD5Utils.getMD5Code(user.getPassword());
        user.setPassword(password);

        //设置用户的角色，默认为普通用户
        user.setRole(RoleEnum.USER.getRole());

        int insertcount=userMapper.insert(user);
        if(insertcount==0){
            //注册失败
            return ServerResponse.serverResponseByFail(StatusEnum.REGISTER_FAIL.getStatus(),StatusEnum.REGISTER_FAIL.getDesc());
        }else {
            return ServerResponse.serverResponseBySuccess("注册成功",null);
        }
    }

    /**
     * 登录的业务逻辑
     */
    @Override
    public ServerResponse loginLogin(String username, String password) {
        /**
         * 参数-用户名非空判断
         */
        if(username==null||username.equals("")){
            return ServerResponse.serverResponseByFail(StatusEnum.USERNAME_NOT_EMPTY.getStatus(),StatusEnum.USERNAME_NOT_EMPTY.getDesc());
        }
        /**
         * 参数-密码非空判断
         */
        if(password==null||password.equals("")){
            return ServerResponse.serverResponseByFail(StatusEnum.PASSWORD_NOT_EMPTY.getStatus(),StatusEnum.PASSWORD_NOT_EMPTY.getDesc());
        }
        /**
         * 判断用户名是否存在
         */
        int count=userMapper.countUsername(username);
        if(count==0){
            //用户名不存在
            return ServerResponse.serverResponseByFail(StatusEnum.USERNAME_NOT_EXISTS.getStatus(),StatusEnum.USERNAME_NOT_EXISTS.getDesc());
        }

        //对明文进行加密
        String psd=MD5Utils.getMD5Code(password);

        /**
         * 根据用户名和密码进行查询
         */
        User user=userMapper.findByUsernameAndPassword(username,psd);
        if(user==null){
            return ServerResponse.serverResponseByFail(StatusEnum.PASSWORD_INCORRENT.getStatus(),StatusEnum.PASSWORD_INCORRENT.getDesc());
        }
        return ServerResponse.serverResponseBySuccess(null,user);
    }
}



