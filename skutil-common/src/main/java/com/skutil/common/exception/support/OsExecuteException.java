package com.skutil.common.exception.support;

import com.skutil.common.exception.UnexpectException;

/**
 * @author zhan yan
 * @date 2022/12/28
 */
public class OsExecuteException extends UnexpectException {

    public OsExecuteException() {
        super("系统内部执行失败");
    }

    public OsExecuteException(String message) {
        super(message);
    }

    public OsExecuteException(Throwable cause) {
        super(cause);
    }

    public OsExecuteException(String message, Throwable cause) {
        super(message, cause);
    }


}
