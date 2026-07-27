package com.lgoshop.common.exception;

/**
 * @描述: 业务异常封装
 * @作者: 太馨元
 * @创建时间: 2026/7/14 14:59
 */
public class LgoBusinessException extends RuntimeException{
    private static final long serialVersionUID=1L;

    private int code;
    private  String msg;

    public  LgoBusinessException(String msg){
        this.code=500;
        this.msg=msg;

    }

    public  LgoBusinessException(int code, String msg){
        this.code=code;
        this.msg=msg;

    }

    public  LgoBusinessException(String formot,Object... args){
        this.code=500;
        this.msg=String.format(formot,args);

    }

    public int getCode(){return code;}


}