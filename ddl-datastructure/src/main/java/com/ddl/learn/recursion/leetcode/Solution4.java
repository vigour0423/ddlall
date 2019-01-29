package com.ddl.learn.recursion.leetcode;/// Leetcode 203. Remove Linked List Elements
/// https://leetcode.com/problems/remove-linked-list-elements/description/

class Solution4 {

    public ListNode removeElements(ListNode head, int val) {
        // 求解最基本问题
        if (head == null) {
            return head;
        }

        // 将原问题转换为更小问题
        ListNode res = removeElements(head.next, val);
        if (head.val == val) {
            //头结点需要删除
            return res;
        } else {
            // 这个head不需要删除，继续连接上链表。
            head.next = res;
            return head;
        }
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution4()).removeElements(head, 6);
        System.out.println(res);
    }
}