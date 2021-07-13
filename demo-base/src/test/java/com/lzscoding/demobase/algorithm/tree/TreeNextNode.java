package com.lzscoding.demobase.algorithm.tree;

import lombok.val;

/**
 * 输⼊⼀个⼆叉树的节点，要求返回按前序遍历的顺序，输⼊节点的下⼀个节点。要求 O(1) 空间复杂度
 */
public class TreeNextNode {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node5.right = node9;
        node3.left = node6;
        node3.right = node7;
        node6.left = node8;
        node2.parent = node1;
        node3.parent = node1;
        node4.parent = node2;
        node5.parent = node2;
        node9.parent = node5;
        node3.parent = node1;
        node6.parent = node3;
        node7.parent = node3;
        node8.parent = node9;

        TreeNode treeNode = nextNode(node7);

        System.out.println(treeNode.val);

    }

    public static TreeNode nextNode(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return node.left;
        }
        if (node.right != null) {
            return node.right;
        }
        while (node.parent != null) {
            TreeNode parent = node.parent;
            if (node == parent.left && parent.right != null) {
                return parent.right;
            }
            node = parent;
        }
        return null;
    }
}
