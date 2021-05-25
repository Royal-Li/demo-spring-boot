package com.lzscoding.demobase.algorithm.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 快速排序的基本思想：
 * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
 * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 */
public class KuaiSuSort {

    public static int[] arr = new int[]{6, 45, 96, 64, 3, 5, 8, 9, 4, 10, 1, 2, 5, -3, 16, 12, 13, 3, 9, 7};

    private static int count;

    public static void main(String[] args) {

        int[] num = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前: " + JSONObject.toJSONString(num));
        QuickSort(num, 0, num.length - 1);
        System.out.println("排序后: " + JSONObject.toJSONString(num));
        System.out.println("数组个数：" + num.length);
        System.out.println("循环次数：" + count);
    }

    /**
     * 快速排序
     *
     * @param num   排序的数组
     * @param left  数组的前针
     * @param right 数组后针
     */
    private static void QuickSort(int[] num, int left, int right) {
        //如果left等于right，即数组只有一个元素，直接返回
        if (left >= right) {
            return;
        }
        //设置最左边的元素为基准值
        int key = num[left];
        //数组中比key小的放在左边，比key大的放在右边，key值下标为i
        int i = left;
        int j = right;
        while (i < j) {
            //j向左移，直到遇到比key小的值
            while (num[j] >= key && i < j) {
                j--;
            }
            //i向右移，直到遇到比key大的值
            while (num[i] <= key && i < j) {
                i++;
            }
            //i和j指向的元素交换
            if (i < j) {
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
            }
        }
        num[left] = num[i];
        num[i] = key;
        count++;
        QuickSort(num, left, i - 1);
        QuickSort(num, i + 1, right);
    }


}
