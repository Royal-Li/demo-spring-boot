package com.lzscoding.demobase.algorithm;


/**
 * 转置链表算法
 *
 * 下面printprintNode方法 中 head = head.next; 没有改变原链表   改变自己的指向
 * 而  reverseList  方法 中 cur.next = pre; 改变了原链表        改变成员变量指向
 *
 * 引申出
 * java 值传递 引用传递 深复制 浅复制的思考
 *
 * 值传递是传递实参副本，函数修改不会影响实参；引用传递是传递实参地址，函数修改会影响实参。(C++)
 * java中只有值传递 引用数据类型 传递的实参是地址
 * https://blog.csdn.net/u014745069/article/details/86649062?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
 *
 */
public class ReverseList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        /**
         * 迭代方法转置链表
         *
         * @param headNode
         * @return
         */
        public static ListNode reverseList(ListNode headNode) {
            ListNode pre = null, cur = headNode, next = null;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }

        /**
         * 递归方法转置链表
         * @param headNode
         * @return
         */
        public static ListNode reverseList2(ListNode headNode) {
            if(headNode == null || headNode.next == null) {
                return headNode;
            }
            ListNode node = reverseList2(headNode.next);
            headNode.next.next = headNode;
            headNode.next = null;
            return node;
        }

    }

    public static void printNode(ListNode head) {
//        ListNode head = listNode;
        while (null != head) {
            System.out.print(head.val + ",");
            head = head.next;

        }
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        ListNode headNode = new ListNode(1);
        ListNode tempNode = headNode;
        for (int i = 2; i <= 10; i++) {
            tempNode.next = new ListNode(i);
            tempNode = tempNode.next;
        }

        printNode(headNode);

        ListNode reverseListHead = new Solution().reverseList(headNode);
        printNode(reverseListHead);
//        ListNode reverseListHead1 = new Solution().reverseList(headNode);
//        printNode(reverseListHead1);

//        ListNode reverseListHead2 = new Solution().reverseList2(headNode);
//        printNode(reverseListHead2);


    }

}





