package sort;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 选择排序法
 * @date 2021/2/26 20:46
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 48, 39, 83, 77, 79};
        sort(arr);
        System.err.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        int temp = 0;
        int index = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            index = i;
            // 获取最小值
            for (int j = index; j < arr.length - 1; j++) {
                if (arr[index] > arr[j + 1]) {
                    index = j + 1;
                }
            }
            // 当前下标如果是正确的，不需要赋值交换，如果不是正确的，再进行交换
            if (index != i) {
                temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
            System.err.printf("第%s次排序，结果是%s\n", (i + 1), Arrays.toString(arr));
        }
    }
}
