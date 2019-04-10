package com.ddl.learn.array;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
       /* System.out.println(Integer.toBinaryString(300));
        System.out.println(Integer.toBinaryString(15 >>> 4));*/
        HashMap<String, Object> map = new HashMap<>(13);
        map.put("ddl", 22);
        map.put("ddll", 33);
        map.put("ddlll", 44);
        map.put(null, null);
        map.put(null, 3);
        System.out.println(map.get("ddlll"));
        System.out.println(map);

     /*   Array<Integer> arr = new Array<>();
        for(int i = 0 ; i < 10 ; i ++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        for(int i = 0 ; i < 4 ; i ++){
            arr.removeFirst();
            System.out.println(arr);
        }*/
    }
}
