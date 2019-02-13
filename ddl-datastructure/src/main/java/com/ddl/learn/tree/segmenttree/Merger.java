package com.ddl.learn.tree.segmenttree;

@FunctionalInterface
public interface Merger<E> {
    E merge(E a, E b);
}
