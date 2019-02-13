package com.ddl.learn.tree.segmenttree;

/// Leetcode 307. Range Sum Query - Mutable
/// https://leetcode-cn.com/problems/range-sum-query-mutable/description/
class NumArray4 {

    private SegmentTree<Integer> segmentTree;


    public NumArray4(int[] nums) {

        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }

    }

    public void update(int i, int val) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Error");
        }
        segmentTree.set(i, val);
    }

    public int sumRange(int i, int j) {

        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null");
        }

        return segmentTree.query(i, j);
    }
}
