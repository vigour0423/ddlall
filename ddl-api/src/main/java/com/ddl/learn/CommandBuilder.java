package com.ddl.learn;


import com.ddl.egg.exception.BusinessException;

import java.util.function.Supplier;

/**
 * description: 事件构建
 * @author dongdongliu
 * @version 1.0
 * @date 2018-10-23 13:34:50
 */
public class CommandBuilder<T extends Command> {

    private Supplier<T> supplier;

    private T command;

    public CommandBuilder(Class<T> typeParameterClass) {
        try {
            command = typeParameterClass.newInstance();
        } catch (Exception e) {
            throw new BusinessException(500, "create instance occur exception.", e);
        }
    }

    public T build() {
        return command;
    }

    public CommandBuilder<T> copyFrom(Command c) {
        command.setModule(c.getModule());
        command.setAction(c.getAction());
        command.setTransactionId(c.getTransactionId());
        command.setFunctionName(c.getFunctionName());
        command.setReason(c.getReason());
        command.setOperatorAccount(c.getOperatorAccount());
        command.setOperatorName(c.getOperatorName());
        return this;
    }

    public CommandBuilder<T> transactionId(String transactionId) {
        command.setTransactionId(transactionId);
        return this;
    }

    public CommandBuilder<T> reason(String reason) {
        command.setReason(reason);
        return this;
    }

    public CommandBuilder<T> action(String action) {
        command.setAction(action);
        return this;
    }

    public CommandBuilder<T> module(String module) {
        command.setModule(module);
        return this;
    }

    public CommandBuilder<T> operator(String name, String account) {
        command.setOperatorName(name);
        command.setOperatorAccount(account);
        return this;
    }
}
