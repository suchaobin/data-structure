package sort;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 快速排序法
 * @date 2021/2/28 09:46
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {3, 1, 48, 39, 83, 77, 79};
        sort(arr, 0, arr.length - 1);
        System.err.println(Arrays.toString(arr));
    }

    /**
     * 快速排序法是取数组一个随机数当做基数，比基数小的去基数左边，比基数大的去基数右边
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void sort(int[] arr, int left, int right) {
        // 基数下标
        int index = (left + right) / 2;
        // 基数
        int pivot = arr[index];
        // 左边扫描的下标
        int l = left;
        // 右边扫描的下标
        int r = right;
        while (l < r) {
            // 找到左边比基数大的下标
            while (arr[l] < pivot) {
                l++;
            }
            // 找到右边比基数小的下标
            while (arr[r] > pivot) {
                r--;
            }
            // 左边指针不能超过右边指针
            if (l >= r) {
                break;
            }
            // l和r下标的元素互换位置
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 防止同样大小的元素重复交换，产生死循环,例如此时l和r下标的元素都和基数相等，就换反复交换，死循环
            // 从右边交换到左边的数如果和基数相等，右边指针要往左移
            if (arr[l] == pivot) {
                r--;
            }
            // 从左边交换到右边的数如果和基数相等，左边指针要右移
            if (arr[r] == pivot) {
                l++;
            }
        }
        // 当l=r的时候，必须l++和r--，否则会堆栈溢出
        if (l == r) {
            l++;
            r--;
        }
        // 重复上面的动作
        if (left < l) {
            sort(arr, left, r);
        }
        if (right > r) {
            sort(arr, l, right);
        }
    }
}
