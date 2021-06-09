package com.lzscoding.demobase.algorithm;

/**
 * 有序数组求两数之和
 */
public class TwoNumSUM {
    public static void main(String[] args) {

        int a[] = {1, 2, 3, 4, 5, 13, 52, 67, 87, 99, 100};
        int[] ints = twoSum(a, 56);
        for (int i : ints) {
            System.out.println(i);
        }

    }

    public static int[] twoSum(int[] num, int sum) {
        int left = 0, right = num.length - 1;

        while (left < right) {
            int temp = num[left] + num[right];
            if (temp == sum) {
                return new int[]{num[left], num[right]};
            } else if (temp > sum) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{};
    }
}
