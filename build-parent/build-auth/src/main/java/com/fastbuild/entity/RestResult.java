package com.fastbuild.entity;

/**
 * Rest消息封装类
 *
 * @auther xinch
 * @create 2018/1/18 19:48
 */
public class RestResult<T> {

    private boolean success;

    private String message;

    private int code;

    private Object dataObject;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getDataObject() {
        return dataObject;
    }

    public void setDataObject(Object dataObject) {
        this.dataObject = dataObject;
    }
}
