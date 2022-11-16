package com.lynn.skutil.spring;

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

    /**
     * @param readOnly whether the transaction is defined as read-only transaction
     */
    @Override
    public void beforeCommit(boolean readOnly) {
        this.runnable.run();
    }

    @Override
    public void beforeCompletion() {
        this.runnable.run();
    }

    @Override
    public void afterCommit() {
        this.runnable.run();
    }

    @Override
    public void afterCompletion(int status) {
        this.runnable.run();
    }
}
