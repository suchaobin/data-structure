package sort;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 堆排序
 * @date 2021/3/1 22:16
 **/
public class HeapSort {

    /**
     * 完成将以i对应的非叶子节点的输调整成大顶堆
     * 例：int[] arr = {4, 6, 8, 5, 9} => i=1 调用adjustHeap 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用，传入i=0，得到新数组{9, 6, 8, 5, 4}
     *
     * @param arr    待排序的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素继续调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        // 非叶子节点下的左子节点=2n+1
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 用子节点的最大值和父节点进行比较大小，如果子节点更大，就对换位置
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            // 如果子节点的值更大，就互换位置
            if (arr[k] > temp) {
                arr[i] = arr[k];
                // 这边的i=k是因为i要指向当前节点的位置，此时当前节点已经和子节点换了位置，所以要给i重新赋值
                i = k;
            } else {
                // 因为堆排序是从左到右，从上到下的排序，所以此时这个以i为父节点的树如果顺序都正常，可以直接退出，不进行排序
                break;
            }
        }
        // 当循环结束的时候，我们已经将以i为父节点的树的最大值，放到了以i为父节点的树的顶部
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        // 从最后一个非叶子节点开始排序
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j >= 0; j--) {
            // 进入循环时堆顶已经是最大值，此时把最大值放到后面去，然后把剩下未排序的重新当大顶堆调整
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            /**
             * 之前从最后一个非叶子节点进行循环排序后，获得了一个大顶堆了，元素换位后，只需要
             * 最顶层开始重新进行一次排序，就又是一个大顶堆了，所以这里的参数永远是0
             */
            adjustHeap(arr, 0, j);
        }
        System.err.println(Arrays.toString(arr));
    }
}
