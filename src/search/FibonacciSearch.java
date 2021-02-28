package search;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 斐波那契查找法（也称为黄金分割查找法）
 * @date 2021/2/28 22:07
 **/
public class FibonacciSearch {
    private int maxSize;
    private int[] fibonacciArr;

    public FibonacciSearch(int maxSize) {
        this.maxSize = maxSize;
        fibonacciArr = new int[maxSize];
        fibonacciArr[0] = 1;
        fibonacciArr[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fibonacciArr[i] = fibonacciArr[i - 1] + fibonacciArr[i - 2];
        }
    }

    /**
     * 核心算法在于f(k) = f(k - 1) + f(k - 2) => f(k) - 1 = [f(k - 1) - 1] + [f(k - 2) - 1] + 1
     * 把最后加的那个1看做是插值查找的mid，所以当一个数组的长度是f(k) - 1的时候（f(k)是斐波那契数组），
     * 可以看做是3个部分，1是黄金分割点mid，分割点前是一个数组，长度是f(k - 1) - 1,分割点后是一个数组，长度是f(k - 2) - 1
     *
     * @param arr
     * @param findValue
     */
    private int search(int[] arr, int findValue) {
        int left = 0;
        int right = arr.length - 1;
        // 先找到和斐波那契数组中元素最接近的下标
        int k = 0;
        while (arr.length > fibonacciArr[k] - 1) {
            k++;
        }
        // arr的数组长度可能不够斐波那契数组长度，所以创建一个临时数组，长度是斐波那契的长度，不足部分被0填充
        int[] temp = Arrays.copyOf(arr, fibonacciArr[k] - 1);
        // 如果之前的长度不够斐波那契数组长度，有被0填充的部分，我们需要进行填充，保证数组是有序
        if (arr.length < fibonacciArr[k]) {
            for (int i = arr.length; i < temp.length; i++) {
                temp[i] = arr[arr.length - 1];
            }
        }
        // 根据当前的黄金分割点进行查找
        while (left <= right) {
            int mid = left + fibonacciArr[k - 1] - 1;
            /**
             * 查找的值在黄金分割点的左边，把黄金分割点的左边看做是一个新的斐波那契数组，再进行分割找新的黄金分割点
             * 左边的数组长度是f(k - 1) - 1 = [f(k - 2) - 1] + [f(k - 3) - 1] + 1，所以k要-1
             * 最右边的索引也要变成mid - 1
             */
            if (findValue < temp[mid]) {
                k -= 1;
                right = mid - 1;
            } else if (findValue > temp[mid]) {
                /**
                 * 查找的值在黄金分割点的右边，把黄金分割点的右边看做是一个新的斐波那契数组，再进行分割找新的黄金分割点
                 * 右边的数组长度是f(k - 2) - 1 = [f(k - 3) - 1] + [f(k - 4) - 1] + 1，所以k要-2
                 * 最左边的索引也要变成mid + 1
                 */
                k -= 2;
                left = mid + 1;
            } else {
                // 如果此时找到了，要进行判断，因为temp数组是扩容的数组，要判断是否超出arr的长度
                // mid是下标位置，不能超过arr.length - 1
                if (mid <= arr.length - 1) {
                    return mid;
                } else {
                    // 如果超出了arr的长度，因为扩容后的值都是以arr的最大值算的，所以返回边界长度即可
                    return right;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 7, 8};
        FibonacciSearch fibonacciSearch = new FibonacciSearch(8);
        System.err.println(fibonacciSearch.search(arr, 3));
        System.err.println(fibonacciSearch.search(arr, 7));
        System.err.println(fibonacciSearch.search(arr, 0));
    }
}
