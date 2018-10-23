package com.ddl.learn;


import com.ddl.egg.exception.BusinessException;
//import com.ddl.egg.common.spring.SpringContextHolder;

import java.io.Serializable;
import java.util.UUID;

/**
 * description:<命令，由客户端事件驱动命令（对外）>
 * @author dongdongliu
 * @date 2018/10/23 13:31
 */
public abstract class Command implements Serializable {

    private String operatorAccount;

    private String operatorName;

    private String transactionId = UUID.randomUUID().toString();

    private String module;

    private String functionName;

    private String action;

    private String reason;

    protected Command() {
    }

    public String getOperatorAccount() {
        return operatorAccount;
    }

    @Deprecated
    public void setOperatorAccount(String operatorAccount) {
        this.operatorAccount = operatorAccount;
    }

    public String getOperatorName() {
        return operatorName;
    }

    @Deprecated
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Deprecated
    protected void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getModule() {
        return module;
    }

    @Deprecated
    public void setModule(String module) {
        this.module = module;
    }

    public String getFunctionName() {
        return functionName;
    }

    @Deprecated
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getAction() {
        return action;
    }

    @Deprecated
    public void setAction(String action) {
        this.action = action;
    }

    public String getReason() {
        return reason;
    }

    @Deprecated
    public void setReason(String reason) {
        this.reason = reason;
    }

    public abstract static class Builder<T extends Builder, K extends Command> {
        protected K command;

        protected abstract T getThis();

        public Builder(Class<K> typeParameterClass) {
            try {
                command = typeParameterClass.newInstance();
            } catch (Exception e) {
                throw new BusinessException(500, "create instance occur exception.", e);
            }
        }

        public T create(Command c) {
            command.setModule(c.getModule());
            command.setAction(c.getAction());
            command.setTransactionId(c.getTransactionId());
            command.setFunctionName(c.getFunctionName());
            command.setReason(c.getReason());
            command.setOperatorAccount(c.getOperatorAccount());
            command.setOperatorName(c.getOperatorName());
            return getThis();
        }

        public K build() {
            return command;
        }

        public T setTransactionId(String transactionId) {
            command.setTransactionId(transactionId);
            return getThis();
        }

        public T setAction(String action) {
            command.setAction(action);
            return getThis();
        }

        public T setOperator(String name, String account) {
            command.setOperatorAccount(account);
            command.setOperatorName(name);
            return getThis();
        }

        public T setReason(String reason) {
            command.setReason(reason);
            return getThis();
        }

        public T setOperator(String account) {
            command.setOperatorAccount(account);
            command.setOperatorName(account);
            return getThis();
        }
    }

    public void publish() {
     /*  CommandDispatchService remoteCommandDispatch = SpringContextHolder.getBean(CommandDispatchService.class);
        remoteCommandDispatch.dispatch(this);*/
    }

    public boolean isOnDubbo() {
        return true;
    }
}
