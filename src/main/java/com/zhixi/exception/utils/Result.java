package com.zhixi.exception.utils;


import com.zhixi.exception.ExceptionCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Author zhangzhixi
 * @Description 一般返回实体
 * @Date 2022-4-7 18:24
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -687690141206758604L;
    private Integer code;
    private String message;
    private T data;

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    /**
     * 带数据成功返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ExceptionCodeEnum.SUCCESS.getCode(), ExceptionCodeEnum.SUCCESS.getDesc(), data);
    }

    /**
     * 不带数据成功返回
     *
     * @return
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 通用错误返回，传入指定的错误枚举
     *
     * @param exceptionCodeEnum
     * @return
     */
    public static <T> Result<T> error(ExceptionCodeEnum exceptionCodeEnum) {
        return new Result<>(exceptionCodeEnum.getCode(), exceptionCodeEnum.getDesc());
    }

    /**
     * 通用错误返回，传入指定的错误枚举，但支持覆盖message
     *
     * @param exceptionCodeEnum
     * @param msg
     * @return
     */
    public static <T> Result<T> error(ExceptionCodeEnum exceptionCodeEnum, String msg) {
        return new Result<>(exceptionCodeEnum.getCode(), msg);
    }

    /**
     * 通用错误返回，只传入message
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg) {
        return new Result<>(ExceptionCodeEnum.ERROR.getCode(), msg);
    }
}
