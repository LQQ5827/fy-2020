package com.neuedu.business.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @类 名： ServerResponse <br/>
 * @描 述： 返回前端的高可用对象<br/>
 * @日 期： 2020/2/15 14:41<br/>
 * @作 者： 鼠小倩<br/>
 * @版 本： 1.0.0
 * @since JDK 1.8
 */
//@JsonInclude(JsonInclude.Include.NON_EMPTY)  返回去除空字段的值
public class ServerResponse<T> {
    private int status; //状态
    private String msg; //错误信息
    private T data;

    private ServerResponse(){}
    private ServerResponse(int status){
        this.status=status;
    }
    private  ServerResponse(int status,String msg){
        this.status=status;
        this.msg=msg;
    }
    private  ServerResponse(int status,String msg,T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    /**
     * get、set方法
     */
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     *当接口调用成功
     */
    public static ServerResponse serverResponseBySuccess(){
        return new ServerResponse(0);
    }
    public static <T> ServerResponse serverResponseBySuccess(String msg,T data){
        return new ServerResponse(0,msg,data);
    }
    /**
     * 当接口调用失败
     */
    public static ServerResponse serverResponseByFail(int status,String msg){
        return new ServerResponse(status,msg);
    }
    /**
     * 判断接口是否调用成功
     */
    public boolean isSucess() {
        return this.status == 0;
    }
}



