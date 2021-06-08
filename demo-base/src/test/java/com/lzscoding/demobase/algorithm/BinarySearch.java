package com.lzscoding.demobase.algorithm;

/**
 * 二分查找 递归非递归
 */
public class BinarySearch {

    public static void main(String[] args) {
        int a[] = {11, 22, 33, 44, 55, 66, 77, 88, 99};

        System.out.println(search(a, 55));
        System.out.println(diguiSearch(a, 55, 0, a.length - 1));
    }

    public static int diguiSearch(int num[], int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (num[mid] == target) {
            return mid;
        } else if (num[mid] > target) {
            return diguiSearch(num, target, left, mid - 1);
        } else {
            return diguiSearch(num, target, mid + 1, right);
        }

    }

    public static int search(int num[], int target) {
        int left = 0, right = num.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (num[mid] == target) {
                return mid;
            }
            if (num[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

}
