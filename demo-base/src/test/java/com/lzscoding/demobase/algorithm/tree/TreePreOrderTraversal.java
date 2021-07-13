package com.lzscoding.demobase.algorithm.tree;

import java.util.Stack;

/**
 * 二叉树的前序遍历
 * 1 递归
 * 2 栈
 */
public class TreePreOrderTraversal {
    public static void main(String[] args) {

    }


    public static void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (stack.empty()) {
            TreeNode pop = stack.pop();
            System.out.println(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    public static void preorderTraversal1(TreeNode root) {
        if (root != null) {
            System.out.println(root);
        }
        preorderTraversal(root.left);
        preorderTraversal(root.right);

    }
}
