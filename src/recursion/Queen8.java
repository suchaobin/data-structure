package recursion;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 8皇后问题
 * @date 2021/2/26 20:06
 **/
public class Queen8 {
    private static final int MAX_SIZE = 8;
    // 用一个数组来存放答案，数组的下标+1就是对应的行数，对应的val就是对应的列
    private static final int[] ARR = new int[MAX_SIZE];
    private static int count = 0;

    public static void main(String[] args) {
        placeQueen(0);
        System.err.println("count = " + count);
    }

    /**
     * 放置第n-1个皇后
     *
     * @param n 第n-1个皇后
     */
    public static void placeQueen(int n) {
        if (n == MAX_SIZE) {
            count++;
            System.err.println(Arrays.toString(ARR));
            return;
        }
        for (int i = 0; i < MAX_SIZE; i++) {
            ARR[n] = i;
            if (judge(n)) {
                placeQueen(n + 1);
            }
        }
    }

    /**
     * 判断第n个皇后是否符合规则
     *
     * @param n 第n个皇后
     * @return 是否符合规则
     */
    public static boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 同一列有冲突
            if (ARR[i] == ARR[n]) {
                return false;
            }
            // 根据两点求斜率，斜率是+-1的时候是斜线，以此来判断是否在斜线上
            if (Math.abs(n - i) == Math.abs(ARR[n] - ARR[i])) {
                return false;
            }
        }
        return true;
    }
}
