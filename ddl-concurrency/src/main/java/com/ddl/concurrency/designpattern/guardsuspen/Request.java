package com.ddl.concurrency.designpattern.guardsuspen;

/**
 * 包裹
 */
public class Request {
    final private String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
