package com.ddl.learn.tree.trie;

import java.util.TreeMap;

public class Trie {

    /**
     * 节点类：isWord，Map<c, node>
     */
    private class Node {
        public boolean isWord;

        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    /**
     * 根节点
     */
    private Node root;

    /**
     * 存储了多少个单词
     */
    private int size;

    /**
     * Trie默认构造函数
     */
    public Trie() {
        root = new Node();
        size = 0;
    }

    /**
     * 获得Trie中存储的单词数量
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 向Trie中添加一个新的单词word
     * @param word
     */
    public void add(String word) {
        // 非递归写法，字符串拆成字符
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }


    /**
     * 查询单词word是否在Trie中
     * @param word
     * @return
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        // 比如trie中有panda，查pan。虽然到节点，但是并没有这个单词。
        return cur.isWord;
    }


    /**
     * 查询是否在Trie中有单词以prefix为前缀
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix) {

        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        // 单词本身也是该单词的前缀。
        return true;
    }


}
