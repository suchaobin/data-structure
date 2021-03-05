package algorithm;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description TODO
 * @date 2021/3/4 15:24
 **/
public class KMPAlgorithm {

    public static void main(String[] args) {
        // A： 尚
        // B: 硅
        // C: 谷
        // D: 你
        // E: 好
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        //String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        //String str2 = "尚硅谷你尚硅你~";
        //String str2 = "硅硅谷~";


        int[] next = KMP.getKmpNext(str2);
        int res = KMP.kmpSearch(str1, str2, next);

        System.out.println("子串在源字符串的index=" + res);

        System.err.println(Arrays.toString(next));

    }

    private static class KMP {

        /**
         * kmp查找
         *
         * @param str  原字符串
         * @param dest 模式串
         * @param next next数组
         * @return 开始的下标，找不到返回-1
         */
        private static int kmpSearch(String str, String dest, int[] next) {
            // i是str的指针，j是dest的指针
            for (int i = 0, j = 0; i < str.length(); i++) {
                while (j > 0 && str.charAt(i) != dest.charAt(j)) {
                    j = next[j - 1];
                }
                if (str.charAt(i) == dest.charAt(j)) {
                    j++;
                }
                if (j == dest.length()) {
                    return i - j + 1;
                }
            }
            return -1;
        }

        /**
         * 根据模式串获得kmp的next数组
         *
         * @param dest 模式串
         * @return kmp的next数组
         */
        private static int[] getKmpNext(String dest) {
            int[] next = new int[dest.length()];
            next[0] = 0;
            // 从下标1开始，因为下标0的值固定是0
            for (int i = 1, j = 0; i < dest.length(); i++) {
                // 不等并且下标没有出问题，就循环套公式
                while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                    j = next[j - 1];
                }
                // 相同就加一
                if (dest.charAt(i) == dest.charAt(j)) {
                    j++;
                }
                // 进行赋值
                next[i] = j;
            }
            return next;
        }
    }
}
