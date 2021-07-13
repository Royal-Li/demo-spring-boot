package com.lzscoding.demobase.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 滑动窗口
 */
public class HuaDongChuangKou {
    public static void main(String[] args) {
        int a[] = new int[]{2,3,4,2,6,2,5,1};
        System.out.println(huaDongChuangKou(a,3));

    }

    /**
     *
     * @param num   数组大小
     * @param size    滑动窗口大小
     * @return
     */
    public static List huaDongChuangKou(int [] num, int size) {

        if (num == null || num.length == 0 || size <= 0 || num.length < size) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> result = new ArrayList<>();
        //双端队列，用来记录每个窗口的最大值下标
        LinkedList<Integer> qmax = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            while (!qmax.isEmpty() && num[qmax.peekLast()] < num[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            //判断队首元素是否过期
            if (qmax.peekFirst() == i - size) {
                qmax.pollFirst();
            }
            //向result列表中加入元素
            if (i >= size - 1) {
                result.add(num[qmax.peekFirst()]);
            }
        }
        return result;
    }
}
