package com.lzscoding.demobase.algorithm.link;

import java.util.Random;

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
            if (l1.val <= l2.val) {
                pre.next = l1;
                pre = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                pre = l2;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            pre.next = l2;
        }
        if (l2 == null) {
            pre.next = l1;
        }
        return head.next;
    }

    public static ListNode createList() {
        ListNode header = new ListNode(0);
        ListNode temp = header;
        for (int i = 0; i < 1000; i += 100) {
            temp.next = new ListNode(i + new Random().nextInt(100));
            temp = temp.next;
        }
        return header;
    }

    public static void printList(ListNode header) {
        while (header != null) {
            System.out.print(header.val + ",");
            header = header.next;
        }
        System.out.println("");
    }


    public static void main(String[] args) {
        ListNode list1 = createList();
        ListNode list2 = createList();
        printList(list1);
        printList(list2);

        ListNode mergeTwoList = mergeTwoList(list1, list2);
        printList(mergeTwoList);

    }
}
