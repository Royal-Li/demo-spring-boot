package com.lzscoding.demobase.algorithm.link;

/**
 * 判断链表是否有环
 * 快慢指针思想 判断链表是否有环
 * 时针和分针 总会相交
 */
public class CycleLink {

    public static Boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            try {
                slow = slow.next;
                fast = fast.next.next;
            } catch (Exception e) {
                return false;
            }
            if (slow == fast) {
                return true;
            }
        }
    }

    public static void main(String[] args) {

    }
}
