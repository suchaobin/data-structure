package sort;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 希尔排序
 * @date 2021/2/28 09:04
 **/
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 48, 39, 83, 77, 79};
        sort2(arr);
        System.err.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序法之插入法（推荐使用）
     *
     * @param arr 数组
     */
    private static void sort(int[] arr) {
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的数组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
            count++;
            System.err.printf("第%s次排序，结果是%s\n", count, Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序之交换法（只作为学习，慢）
     *
     * @param arr 数组
     */
    private static void sort2(int[] arr) {
        int count = 0;
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            count++;
            System.err.printf("第%s次排序，结果是%s\n", count, Arrays.toString(arr));
        }
    }
}
