package com.ddl.learn.linked;

/**
 * @author liuddl
 * @version 1.0
 * @date 2019-01-24 11:31:54
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2, 888);
        System.out.println(linkedList);
        linkedList.remove(2);
        System.out.println(linkedList);
    }
}
