package sort;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 插入排序法
 * @date 2021/2/26 20:56
 **/
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 48, 39, 83, 77, 79};
        sort(arr);
        System.err.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        // 因为插入排序法要把第一个元素当有序列表，所以从下标1开始遍历
        // 取出第i个元素和之前的元素进行比较，选择正确的位置插入
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            // 当要插入的下标>=0时，并且当前要插入的元素小于上一位的元素，就把上一个位元素往后移
            while (insertIndex >= 0 && arr[i] < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
            System.err.printf("第%s次排序，结果是%s\n", i, Arrays.toString(arr));
        }
    }
}
