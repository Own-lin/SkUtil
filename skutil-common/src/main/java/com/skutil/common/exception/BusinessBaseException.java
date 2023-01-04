package com.skutil.common.exception;

/**
 * 所有非必要catch异常的基类
 * @author zhan yan
 * @date 2022/12/28
 */
public class BusinessBaseException extends RuntimeException{

    public BusinessBaseException() {
        super("业务处理异常");
    }

    public BusinessBaseException(String msg) {
        super(msg);
    }

    public BusinessBaseException(Throwable cause) {
        super(cause);
    }

    public BusinessBaseException(Exception e, String msg) {
        super(msg, e);
    }
}
