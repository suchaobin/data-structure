package sort;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 冒泡排序
 * @date 2021/2/26 20:31
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 48, 39, 83, 77, 79};
        sort(arr);
        System.err.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        // 本轮是否进行了交换
        boolean exchange = false;
        // 进行n-1次排序，确定了n-1个元素的顺序，最后一个元素的顺序就可以不用管，也可以确定所有元素的顺序
        for (int i = 0; i < arr.length - 1; i++) {
            // 进行了i次排序后，就会有i个元素不再需要参与排序，其他元素参与排序即可
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，就需要进行交换
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    exchange = true;
                }
            }
            if (!exchange) {
                break;
            } else {
                exchange = false;
            }
        }
    }
}
