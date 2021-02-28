package sort;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 基数排序法
 * @date 2021/2/28 11:55
 **/
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 48, 39, 83, 77, 79};
        sort(arr);
        System.err.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        // 先求出数组内的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        // 创建二维数组表示10个桶，因为每个桶的数量不确定，所以只能默认使用最大
        int[][] bucket = new int[10][arr.length];
        // 创建一维数组表示每个桶的有效个数
        int[] bucketCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 把数据放到桶里
            for (int j = 0; j < arr.length; j++) {
                // 放到第几个桶
                int digit = arr[j] / n % 10;
                bucket[digit][bucketCounts[digit]] = arr[j];
                // 有效长度+1
                bucketCounts[digit]++;
            }
            // 从桶里取出数据
            // 放回原数组的下标
            int index = 0;
            for (int k = 0; k < bucketCounts.length; k++) {
                // 第k个桶的有效长度>0，即里面有数据
                if (bucketCounts[k] > 0) {
                    // 把桶里的元素按加入的顺序取出，放回原数组
                    for (int m = 0; m < bucketCounts[k]; m++) {
                        arr[index] = bucket[k][m];
                        index++;
                    }
                    // 放回数据后，桶的有效长度要重置为0
                    bucketCounts[k] = 0;
                }
            }
        }
    }
}
