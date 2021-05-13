package com.lzscoding.demobase.algorithm.link;

/**
 * 合并两个有序链表
 */

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

public class MergeTwoList {

    public static ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                pre.next = l1;
                pre = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                pre = l2;
                l2 = l2.next;
            }
        }
        if (l1.next == null) {
            pre.next = l2;
        }
        if (l2.next == null) {
            pre.next = l1;
        }
        return head.next;

    }

    public static void main(String[] args) {

    }
}
