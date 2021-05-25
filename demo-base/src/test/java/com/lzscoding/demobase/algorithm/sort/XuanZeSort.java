package com.lzscoding.demobase.algorithm.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 选择排序（Selection Sort）
 * 选择排序(Selection-sort)是一种简单直观的排序算法。
 * 它的工作原理：首先在未排序序列中找到最小（大）元素，
 * 存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，
 * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 */
public class XuanZeSort {

    public static int[] arr = new int[]{6, 45, 96, 64, 3, 5, 8, 9, 4, 10, 1, 2, 5, -3, 16, 12, 13, 3, 9, 7};

    public static void main(String[] args) {

        int minIndex, temp;

        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        System.out.println(JSONObject.toJSONString(arr));
    }
}
