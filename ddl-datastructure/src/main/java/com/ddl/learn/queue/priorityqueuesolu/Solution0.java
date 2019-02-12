package com.ddl.learn.queue.priorityqueuesolu;

import com.ddl.learn.queue.PriorityQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * description: 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 347. Top K Frequent Elements
 * https://leetcode-cn.com/problems/top-k-frequent-elements/description/
 * @author liuddl
 * @version 1.0
 * @date 2019-02-12 16:10:21
 */
class Solution0 {

    private class Freq implements Comparable<Freq> {

        public int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            //  return Integer.compare(this.freq, another.freq);
            if (this.freq < another.freq) {
                return 1;
            } else if (this.freq > another.freq) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for (int key : map.keySet()) {
            if (pq.getSize() < k) {
                pq.enqueue(new Freq(key, map.get(key)));
            } else if (map.get(key) > pq.getFront().freq) {
                pq.dequeue();
                pq.enqueue(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.dequeue().e);
        }
        return res;
    }

    private static void printList(List<Integer> nums) {
        for (Integer num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        printList((new Solution0()).topKFrequent(nums, k));
    }
}
