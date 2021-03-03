package algorithm;

/**
 * @author suchaobin
 * @description 汉诺塔游戏
 * @date 2021/3/3 22:44
 **/
public class HanoiTower {
    private static int count = 0;

    /**
     * 核心思想就是当盘子数量>=2的时候，把盘子看成2部分，一部分是最底下的盘子，一部分就是其他盘子，这样进行排序
     */
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
        System.err.println("count = " + count);
    }

    /**
     * 汉诺塔游戏，有num个盘子在A上，从a移动到c，中间借助于b
     *
     * @param num 几个盘子
     * @param a   塔A的名字
     * @param b   塔B的名字
     * @param c   塔C的名字
     */
    private static void hanoiTower(int num, char a, char b, char c) {
        // 只有一个盘子，直接从A移到C
        if (num == 1) {
            count++;
            System.out.println("第1个盘子从" + a + "->" + c);
        } else {
            /**
             * 如果盘子的数量>=2，每次看成2个盘子，一个是最下面的盘子，剩下的就是其他的盘子
             * 2个盘子是上面的盘子移到B，下面的盘子移到C，最后再把上面的盘子移到C
             */
            // 把除了最底下的盘子外，其他盘子从a移动到b，中间借助于c
            hanoiTower(num - 1, a, c, b);
            // 最底下的盘子从a移动到c
            count++;
            System.out.println("第" + num + "个盘子从" + a + "->" + c);
            // 把其他盘子从b移动到c，中间借助于a
            hanoiTower(num - 1, b, a, c);
        }
    }
}
