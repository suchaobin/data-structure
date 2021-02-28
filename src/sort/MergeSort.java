package sort;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 归并排序法
 * @date 2021/2/28 11:32
 **/
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {3, 1, 48, 39, 83, 77, 79};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.err.println(Arrays.toString(arr));
    }

    /**
     * 归并排序法
     */
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并
     *
     * @param arr   要排序的数组
     * @param left  左边索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  临时数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;
        int j = mid + 1;
        // 临时数组的索引
        int t = 0;
        while (l <= mid && j <= right) {
            if (arr[l] < arr[j]) {
                temp[t] = arr[l];
                l += 1;
                t += 1;
            } else {
                temp[t] = arr[j];
                j += 1;
                t += 1;
            }
        }
        // 左边还有剩余的值
        while (l <= mid) {
            temp[t] = arr[l];
            l += 1;
            t += 1;
        }
        // 右边还有剩余的值
        while (j <= right) {
            temp[t] = arr[j];
            j += 1;
            t += 1;
        }
        // 将临时数组的值赋给原来的数组
        t = 0;
        while (left <= right) {
            arr[left] = temp[t];
            t += 1;
            left += 1;
        }
    }
}
