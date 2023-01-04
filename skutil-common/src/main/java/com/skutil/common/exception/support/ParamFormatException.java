package com.skutil.common.exception.support;

import com.skutil.common.exception.BusinessBaseException;

/**
 * @author zhan yan
 * @date 2022/12/30
 */
public class ParamFormatException extends BusinessBaseException {

    public ParamFormatException() {
        super("参数格式化错误");
    }

    public ParamFormatException(String msg) {
        super(msg);
    }

    public ParamFormatException(Throwable cause) {
        super(cause);
    }

    public ParamFormatException(Exception e, String msg) {
        super(e, msg);
    }
}
