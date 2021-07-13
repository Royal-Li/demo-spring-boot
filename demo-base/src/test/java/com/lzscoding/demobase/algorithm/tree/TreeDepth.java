package com.lzscoding.demobase.algorithm.tree;

/**
 * 求二叉树深度
 */
class TreeNode {
    TreeNode left;
    TreeNode right;
    TreeNode parent;
    int val;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class TreeDepth {

    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        int depth = maxDepth(new TreeNode(100));
        System.out.println(depth);
    }
}
