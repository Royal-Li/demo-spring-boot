package com.lzscoding.demobase.algorithm.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。
 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 */
public class ChaRuSort {

    public static int[] arr = new int[]{6, 45, 96, 64, 3, 5, 8, 9, 4, 10, 1, 2, 5, -3, 16, 12, 13, 3, 9, 7};

    public static void main(String[] args) {
        int preIndex, current, temp;
        for (int i = 1; i < arr.length; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && current < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        System.out.println(JSONObject.toJSONString(arr));
    }
}
