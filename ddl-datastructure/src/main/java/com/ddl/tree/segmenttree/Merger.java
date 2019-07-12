package com.ddl.tree.segmenttree;

@FunctionalInterface
public interface Merger<E> {
    E merge(E a, E b);
}
