package com.ddl.concurrency.multithreading.runnable;


@FunctionalInterface
public interface CalculatorStrategy {

    double calculate(double salary, double bonus);
}
