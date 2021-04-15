package com.lzscoding.demobase.algorithm;

public class ReverseList2 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        int n = 2;
        ListNode head = listNode;
        while (n < 10) {
            head.next = new ListNode(n);
            head = head.next;
            n++;
        }
        printfListNode(listNode);
        reverseBetween(listNode, 2, 4);
        printfListNode(listNode);
    }

    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public static void printfListNode(ListNode listNode) {
        ListNode head = listNode;
        while (null != head) {
            System.out.print(head.value);
            System.out.print(",");
            head = head.next;
        }
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < m; i++) {
            pre = pre.next;
        }
        head = pre.next;
        System.out.println(dummy);
        for (int i = m; i < n; i++) {
            ListNode nex = head.next;
            head.next = nex.next;
            nex.next = pre.next;
            pre.next = nex;
        }
        return dummy.next;
    }
}