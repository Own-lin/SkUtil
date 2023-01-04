package com.skutil.common.exception.support;

import com.skutil.common.exception.BusinessBaseException;

/**
 * 业务处理中的参数异常
 * @author zhan yan
 * @date 2022/12/28
 */
public class BuzParamException extends BusinessBaseException {

    public BuzParamException(){
        super("参数有误");
    }

    public BuzParamException(String msg){
        super(msg);
    }

    public BuzParamException(Exception e, String msg){
        super(e, msg);
    }


}
