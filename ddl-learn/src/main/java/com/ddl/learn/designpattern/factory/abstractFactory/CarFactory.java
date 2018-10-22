package com.ddl.learn.designpattern.factory.abstractFactory;

public interface CarFactory {
    Engine createEngine();

    Seat createSeat();

    Tyre createTyre();
}

