package com.neuedu.business.service.impl;

import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.common.StatusEnum;
import com.neuedu.business.dao.CategoryMapper;
import com.neuedu.business.pojo.Category;
import com.neuedu.business.service.ICategoryService;
import com.neuedu.business.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @类 名： CategoryServiceImpl <br/>
 * @描 述： <br/>
 * @日 期： 2020/2/16 15:20<br/>
 * @作 者： 鼠小倩<br/>
 * @版 本： 1.0.0
 * @since JDK 1.8
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse addCategory(Integer parentId, String categoryName) {
        /**
         * 参数非空校验
         */
        if(categoryName==null||categoryName.equals("")){
            return ServerResponse.serverResponseByFail(StatusEnum.CATEGORY_NAME_NOT_EMPTY.getStatus(),StatusEnum.CATEGORY_NAME_NOT_EMPTY.getDesc());
        }
        /**
         * 生成类别实体类
         */
        Category category=new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        /**
         * 插入到数据库
         */
        int result=categoryMapper.insert(category);
        if(result==0){
            //插入失败
            return ServerResponse.serverResponseByFail(StatusEnum.CATEGORY_INSERT_FAIL.getStatus(),StatusEnum.CATEGORY_INSERT_FAIL.getDesc());
        }
        return ServerResponse.serverResponseBySuccess();
    }

    @Override
    public ServerResponse set_category_name(Integer categoryId, String categoryName) {
        /**
         * 1.参数非空校验
         */
        //类别id非空判断
        if(categoryId==null){
            return ServerResponse.serverResponseByFail(StatusEnum.CATEGORYID_NOT_EMPTY.getStatus(),StatusEnum.CATEGORYID_NOT_EMPTY.getDesc());
        }
        //类别名非空判断
        if(categoryName==null||categoryName.equals("")){
            return ServerResponse.serverResponseByFail(StatusEnum.CATEGORY_NAME_NOT_EMPTY.getStatus(),StatusEnum.CATEGORY_NAME_NOT_EMPTY.getDesc());
        }

        /**
         * 2.根据categoryId查询是否存在
         */
        Category category=categoryMapper.selectByPrimaryKey(categoryId);
        if(category==null){
            //类别不存在
            return ServerResponse.serverResponseByFail(StatusEnum.CATEGORY_NOT_EXISTS.getStatus(),StatusEnum.CATEGORY_NOT_EXISTS.getDesc());
        }
        /**
         * 3.修改
         */
        category.setName(categoryName);
        int result=categoryMapper.updateByPrimaryKey(category);
        if(result<=0){
            //修改失败
            return ServerResponse.serverResponseByFail(StatusEnum.CATEGORY_UPDATE_FAIL.getStatus(),StatusEnum.CATEGORY_UPDATE_FAIL.getDesc());
        }
        /**
         * 4.返回结果
         */
        return ServerResponse.serverResponseBySuccess();
    }
}



