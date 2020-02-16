package com.neuedu.business.controller;

import com.neuedu.business.common.Consts;
import com.neuedu.business.common.RoleEnum;
import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.common.StatusEnum;
import com.neuedu.business.pojo.User;
import com.neuedu.business.service.ICategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @类 名： CategoryController <br/>
 * @描 述： <br/>
 * @日 期： 2020/2/16 15:10<br/>
 * @作 者： 鼠小倩<br/>
 * @版 本： 1.0.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/manage/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;
    /**
     * 添加类别
     */
    @RequestMapping("add_category.do")
    public ServerResponse addCategory(@RequestParam (value = "parentId", required = false,defaultValue = "0") Integer parentId,
                                      @RequestParam("categoryName") String categoryName,
                                      HttpSession session){
        //1.先判断用户是否登陆
        User user=(User) session.getAttribute(Consts.USER);
        if(user==null){
            //未登录
            return ServerResponse.serverResponseByFail(StatusEnum.NO_LOGIN.getStatus(),StatusEnum.NO_LOGIN.getDesc());
        }

        //2.已经登陆，判断是否有管理员管理权限
        if(user.getRole()!= RoleEnum.ADMIN.getRole()){
            //是普通用户
            return ServerResponse.serverResponseByFail(StatusEnum.NO_AUTHORUTY.getStatus(),StatusEnum.NO_AUTHORUTY.getDesc());
        }
        return categoryService.addCategory(parentId,categoryName);
    }

    /**
     * 修改品类名称
     */
    @RequestMapping("set_category_name.do")
    public ServerResponse set_category_name(@RequestParam("categoryId")Integer categoryId, @RequestParam("categoryName") String categoryName, HttpSession session){
        //1.先判断用户是否登陆
        User user=(User) session.getAttribute(Consts.USER);
        if(user==null){//未登录
            return ServerResponse.serverResponseByFail(StatusEnum.NO_LOGIN.getStatus(),StatusEnum.NO_LOGIN.getDesc());
        }
        //2.已经登陆，判断是否有管理员管理权限
        if(user.getRole()!= RoleEnum.ADMIN.getRole()){//是普通用户
            return ServerResponse.serverResponseByFail(StatusEnum.NO_AUTHORUTY.getStatus(),StatusEnum.NO_AUTHORUTY.getDesc());
        }
        return categoryService.set_category_name(categoryId,categoryName);
    }

}



