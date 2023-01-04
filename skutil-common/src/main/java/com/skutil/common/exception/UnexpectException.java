package com.skutil.common.exception;

/**
 * 未意料异常的基类，其下的子类为抽象的未意料的事情类型
 * @author zhan yan
 * @date 2022/12/28
 */
public class UnexpectException extends RuntimeException{

    public UnexpectException() {
        super("未意料的异常，请再次重试");
    }

    public UnexpectException(String message) {
        super(message);
    }

    public UnexpectException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectException(Throwable cause) {
        super(cause);
    }
}
