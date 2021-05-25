package com.lzscoding.demobase.algorithm.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 冒泡排序（Bubble Sort）
 * 冒泡排序是一种简单的排序算法。
 * 它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 */
public class MaoPaoSort {

    public static int[] arr = new int[]{6, 45, 96, 64, 3, 5, 8, 9, 4, 10, 1, 2, 5, -3, 16, 12, 13, 3, 9, 7};

    public static void main(String[] args) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(JSONObject.toJSONString(arr));
    }

}
