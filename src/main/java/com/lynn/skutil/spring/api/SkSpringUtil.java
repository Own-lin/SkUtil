package com.lynn.skutil.spring.api;

import com.lynn.skutil.spring.TxCallbackWorker;
import org.springframework.lang.NonNull;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author zhan yan
 * @date 2022/11/12
 */
@SuppressWarnings("unused")
public class SkSpringUtil {

    private SkSpringUtil(){}

    /**
     * 功能：事务回调
     * 执行顺序：beforeCommit —> beforeCompletion —> afterCommit —> afterCompletion
     */
    public static void doTxCallback(@NonNull TxCallbackWorker callbackWorker){
        if (TransactionSynchronizationManager.isActualTransactionActive()){
            TransactionSynchronizationManager.registerSynchronization(callbackWorker);
        }
    }



}
