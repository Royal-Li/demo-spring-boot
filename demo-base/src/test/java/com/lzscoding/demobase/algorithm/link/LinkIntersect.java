package com.lzscoding.demobase.algorithm.link;

/**
 * 判断两个链表有无交点
 */
public class LinkIntersect {

    public static ListNode getIntersectionNode(ListNode head1, ListNode head2) {
        ListNode n1 = head1, n2 = head2;
        while (n1 != n2) {
            n1 = n1.next == null ? head2 : n1.next;
            n2 = n2.next == null ? head1 : n2.next;
        }
        return n1;
    }

    public static void main(String[] args) {
        ListNode headCom = new ListNode(0), temp = headCom;
        for (int i = 1; i < 3; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }

        ListNode head1 = new ListNode(98);
        head1.next = new ListNode(77);
        head1.next.next = headCom;
        ListNode head2 = new ListNode(59);
        head2.next = headCom;

        System.out.println(getIntersectionNode(head1, head2).val);

    }
}
