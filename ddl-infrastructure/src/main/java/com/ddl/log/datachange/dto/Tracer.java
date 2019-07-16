package com.ddl.log.datachange.dto;

import java.util.Date;


public class Tracer {

    private boolean trace = false;

    private String transactionId;

    private String module;

    private String function;

    private String changeType;

    private String reason;

    private String operatorName;

    private String operatorAccount;

    private Date changeDate;

    public Tracer(String transactionId, String module, String function, String changeType, String reason, String operatorName, String operatorAccount) {
        this.trace = true;
        this.transactionId = transactionId;
        this.module = module;
        this.function = function;
        this.changeType = changeType;
        this.reason = reason;
        this.operatorName = operatorName;
        this.operatorAccount = operatorAccount;
        this.changeDate = new Date();
    }

    public boolean isTrace() {
        return trace;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getModule() {
        return module;
    }

    public String getFunction() {
        return function;
    }

    public String getChangeType() {
        return changeType;
    }

    public String getReason() {
        return reason;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public String getOperatorAccount() {
        return operatorAccount;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setTrace(boolean trace) {
        this.trace = trace;
    }
}
