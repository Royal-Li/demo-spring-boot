package com.lzscoding.demobase.algorithm;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://blog.csdn.net/imbestman/article/details/111995273
 * 最小k个数 (大根堆)
 * <p>
 * 保持堆的大小为K，然后遍历数组中的数字，遍历的时候做如下判断：
 * 1. 若目前堆的大小小于K，将当前数字放入堆中。
 * 2. 否则判断当前数字与大根堆堆顶元素的大小关系，如果当前数字比大根堆堆顶还大，这个数就直接跳过；
 * 反之如果当前数字比大根堆堆顶小，先poll掉堆顶，再将该数字放入堆中。
 */
public class MinKNums {
    public static void main(String[] args) {
//        int a[] = {1, 2, 3, 4, 5, 6,};
        int a[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] leastNumbers = getLeastNumbers(a, 4);
        for (int num : leastNumbers) {
            System.out.println(num);
        }

    }


    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()) {//peek()获取但不删除队首元素
                //poll()获取并删除队首元素
                pq.poll();
                pq.offer(num);
            }
        }

        // 返回堆中的元素
        int[] res = new int[pq.size()];
        int idx = 0;
        for (int num : pq) {
            res[idx++] = num;
        }
        return res;
    }


}
