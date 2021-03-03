package algorithm;

/**
 * @author suchaobin
 * @description 二分查找
 * @date 2021/3/3 22:37
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};

        System.err.println(search(arr, 1));
        System.err.println(search(arr, 66));
        System.err.println(search(arr, 67));

        System.err.println("==========");

        System.err.println(search2(arr, 0, arr.length - 1, 1));
        System.err.println(search2(arr, 0, arr.length - 1, 66));
        System.err.println(search2(arr, 0, arr.length - 1, 67));
    }

    /**
     * 二分查找
     *
     * @param arr   数组
     * @param value 查找的值
     * @return 数组下标，不存在返回-1
     */
    private static int search(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == value) {
                return mid;
            }
            // 在左边
            if (arr[mid] > value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int search2(int[] arr, int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (arr[mid] == value) {
            return mid;
        }
        if (arr[mid] > value) {
            return search2(arr, left, mid - 1, value);
        } else {
            return search2(arr, mid + 1, right, value);
        }
    }
}
