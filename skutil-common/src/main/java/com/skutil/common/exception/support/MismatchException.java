package com.skutil.common.exception.support;

import com.skutil.common.exception.BusinessBaseException;

/**
 * @author zhan yan
 * @date 2022/12/30
 */
public class MismatchException extends BusinessBaseException {

    public MismatchException() {
        super("类型不匹配");
    }

    public MismatchException(String msg) {
        super(msg);
    }

    public MismatchException(Throwable cause) {
        super(cause);
    }

    public MismatchException(Exception e, String msg) {
        super(e, msg);
    }

}
