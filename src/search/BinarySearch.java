package search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suchaobin
 * @description 二分查找法（必须建立在有序数列的基础上）
 * @date 2021/2/28 21:35
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 3, 3, 6, 7};
        System.err.println(search(arr, 0, arr.length - 1, 3));
        System.err.println(search(arr, 0, arr.length - 1, 7));
    }

    private static List<Integer> search(int[] arr, int left, int right, int findValue) {
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        // 查找的值在左边，向左递归
        if (findValue < arr[mid]) {
            return search(arr, left, mid - 1, findValue);
        } else if (findValue > arr[mid]) {
            // 查找的值在右边，向右递归
            return search(arr, mid + 1, right, findValue);
        } else {
            // 直到到值的时候创建集合，用于存储下标返回
            List<Integer> indexList = new ArrayList<>();
            // arr[mid]和查找的值相等，因为是有序数列，所以从左右两边找，直到和查找的值不相等为止
            // 从mid位置往左边扫描
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findValue) {
                indexList.add(temp);
                temp--;
            }
            // 从mid位置往右边扫描
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
