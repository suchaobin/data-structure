package search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suchaobin
 * @description 插值查找算法（类似于二分查找，同样要建立在有序数组的基础上，不过是把数据按几等分来查而已，如果是等差数列，推荐使用）
 * @date 2021/2/28 21:50
 **/
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 3, 3, 6, 7};
        System.err.println(search(arr, 0, arr.length - 1, 3));
        System.err.println(search(arr, 0, arr.length - 1, 7));
        System.err.println(search(arr, 0, arr.length - 1, 38));
    }

    private static List<Integer> search(int[] arr, int left, int right, int findValue) {
        // 除了要判断left小于right外，还得规定findValue的值不能超过最大值，不能小于最小值
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return new ArrayList<>();
        }
        /**
         * 公式推导: mid = (l + r) / 2
         * mid = l + (r - l) / 2
         * 这边乘以1/2理解为(arr[mid] - arr[l]) / (arr[right] - arr[left])
         * 所以这边的1/2替换为(findValue - arr[l])/(arr[right] - arr[left])
         * mid值找到后和二分法就一样了
         */
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        // 比arr[mid]大，要向右递归
        if (findValue > arr[mid]) {
            return search(arr, mid + 1, right, findValue);
        } else if (findValue < arr[mid]) {
            return search(arr, left, mid - 1, findValue);
        } else {
            // 已经查找到下标了，创建一个用来返回的下标集合
            List<Integer> indexList = new ArrayList<>();
            // 值相等的时候，因为是有序数组，所以向左右两边一个个判定是否和查找的值相等
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findValue) {
                indexList.add(temp);
                temp--;
            }
            temp = mid + 1;
            while (temp <= right && arr[temp] == findValue) {
                indexList.add(temp);
                temp++;
            }
            indexList.add(mid);
            return indexList;
        }
    }
}
