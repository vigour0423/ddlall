package com.ddl.learn.infrastructure.eventbus;

@IgnoreForceConsume
public class TestEvent {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}