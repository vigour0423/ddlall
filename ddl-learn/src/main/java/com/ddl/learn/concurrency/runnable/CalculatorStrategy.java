package com.ddl.learn.concurrency.runnable;


@FunctionalInterface
public interface CalculatorStrategy {

    double calculate(double salary, double bonus);
}
