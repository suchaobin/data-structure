package search;

/**
 * @author suchaobin
 * @description 顺序查找（线性查找）
 * @date 2021/2/28 21:33
 **/
public class OrderSearch {
    public static void main(String[] args) {
        int[] arr = {3, 1, 48, 39, 83, 77, 79};
        System.err.println(search(arr, 39));
        System.err.println(search(arr, 38));
    }

    private static int search(int[] arr, int findValue) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == findValue) {
                return i;
            }
        }
        return -1;
    }
}
