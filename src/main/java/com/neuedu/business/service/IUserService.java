package com.neuedu.business.service;

import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.pojo.User;

public interface IUserService {
    /**
     * 处理注册业务逻辑
     */
    public ServerResponse registerLogin(User user);
    /**
     * 处理登录的业务逻辑
     */
    ServerResponse loginLogin(String username,String password);
}
