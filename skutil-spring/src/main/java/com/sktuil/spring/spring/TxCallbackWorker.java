package com.sktuil.spring.spring;

import org.springframework.transaction.support.TransactionSynchronization;

/**
 * @author zhan yan
 * @date 2022/11/12
 */
public class TxCallbackWorker implements TransactionSynchronization {

    private final Runnable runnable;

    public TxCallbackWorker(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void afterCompletion(int status) {
        this.runnable.run();
    }
}
