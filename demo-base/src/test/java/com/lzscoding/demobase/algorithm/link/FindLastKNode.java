package com.lzscoding.demobase.algorithm.link;

/**
 * 找到倒数第K个节点
 * <p>
 * 快慢指针，先让快指针走k步，然后两个指针同步走，当快指针走到头时，慢指针就是链表倒数第k个节点
 */
public class FindLastKNode {

    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode firstGo = head, nextGo = head;
        while (k > 0 && firstGo != null) {
            k--;
            firstGo = firstGo.next;
        }
        while (firstGo != null) {
            firstGo = firstGo.next;
            nextGo = nextGo.next;
        }
        return nextGo;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0), temp = head;
        for (int i = 1; i <= 10; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        System.out.println(getKthFromEnd(head, 4).val);

    }
}
