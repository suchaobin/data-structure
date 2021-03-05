package algorithm;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 背包问题
 * @date 2021/3/4 14:22
 **/
public class KnapsackProblem {

    public static void main(String[] args) {
        // 物品重量
        int[] weight = {1, 4, 3};
        // 物品价值
        int[] val = {1500, 3000, 2000};
        // 背包容量
        int capacity = 4;
        // 商品数量
        int n = val.length;
        // 背包容量和商品组成的二维数组，对应的值表示当前背包容量和当前商品数量组成的最大价值
        int[][] bag = new int[n + 1][capacity + 1];
        // 路径组成的二维数组，表示哪个商品在背包容量多少的时候被装入
        int[][] path = new int[n + 1][capacity + 1];
        // 没有商品或者背包容量是0的时候，最大价值也必定是0
        for (int i = 0; i < bag.length; i++) {
            // 第一列为0
            bag[i][0] = 0;
        }
        for (int i = 0; i < bag[0].length; i++) {
            // 第一行为0
            bag[0][i] = 0;
        }
        // 生成商品和背包关系的价值表
        for (int i = 1; i < bag.length; i++) {
            for (int j = 1; j < bag[0].length; j++) {
                // 当前新加入的商品的重量
                int w = weight[i - 1];
                // 新加入的商品重量超出背包容量，相当于我只能用当前容量装之前的商品
                if (w > j) {
                    bag[i][j] = bag[i - 1][j];
                } else {
                    // 如果可以容纳，就要计算价值进行比较
                    int value = val[i - 1] + bag[i - 1][j - weight[i - 1]];
                    // 价值比之前的高，就用新计算的
                    if (value > bag[i - 1][j]) {
                        bag[i][j] = value;
                        // 每次加入新商品要添加记录
                        path[i][j] = 1;
                    } else {
                        // 价值没之前的高，就用之前的策略
                        bag[i][j] = bag[i - 1][j];
                    }
                }
            }
        }
        // 打印价值表
        for (int i = 0; i < bag.length; i++) {
            System.err.println(Arrays.toString(bag[i]));
        }

        // 因为每次有新商品都会有新的加入记录，所以要倒序输出，输出后还要扣掉商品重量继续循环
        int i = bag.length - 1;
        int j = bag[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] > 0) {
                System.err.println("第" + i + "个商品装入");
                j -= weight[i - 1];
            }
            i--;
        }
    }
}
