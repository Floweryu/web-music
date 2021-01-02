package com.buct.music.result;

import lombok.Data;

/**
 * @author Zhang JunFeng
 * @date 2020/12/30 21:15
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    /**
     * 成功时构造函数
     * @param data  成功返回data
     */
    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    /**
     * 成功时无数据返回构造函数
     * @param code  状态码
     * @param msg   返回信息
     */
    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 自定义返回信息
     * @param code  状态码
     * @param msg   信息
     * @param data  数据
     */
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 失败时构造函数
     * @param codeMsg   状态码和错误信息
     */
    private Result(CodeMsg codeMsg) {
        if (codeMsg == null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }
    /**
     * 成功时调用
     * @param data  返回值
     * @param <T>   类型
     * @return  Result对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> success(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }

    /**
     * 成功时无数据返回
     * @param code  状态码
     * @param msg   返回信息
     * @return  Result对象
     */
    public static <T> Result<T> success(int code, String msg) {
        return new Result<>(code, msg);
    }

    /**
     * 自定义信息
     * @param code  状态码
     * @param msg   信息
     * @param data  数据
     */
    public static <T> Result<T> success(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    /**
     * 失败时调用
     * @param codeMsg   状态码和错误信息
     * @return  Result对象
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }
}
