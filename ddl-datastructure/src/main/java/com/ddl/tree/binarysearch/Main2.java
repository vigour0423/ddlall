package com.ddl.tree.binarysearch;

import java.util.ArrayList;
import java.util.Random;

public class Main2 {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 5;

        // test removeMin
     /*   for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10));
        }*/
        bst.add(6);
        bst.add(4);
        bst.add(5);
        bst.add(3);
        bst.add(7);
        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMin());
        }

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) > nums.get(i)) {
                throw new IllegalArgumentException("Error!");
            }
        }
        System.out.println("removeMin test completed.");


        // test removeMax
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMax());
        }

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) < nums.get(i)) {
                throw new IllegalArgumentException("Error!");
            }
        }
        System.out.println("removeMax test completed.");
    }
}
