package recursion;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 迷宫
 * @date 2021/2/26 19:37
 **/
public class Maze {
    public static void main(String[] args) {
        // 创建迷宫地图，点的值是 0是没走过的路，1表示是墙壁，2是走过的路，3是已经走过但是走不通的路
        int[][] map = new int[8][7];
        // 第1行和第8行是墙壁
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 第1列和第7列是墙壁
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置其他墙壁的点
        map[3][1] = 1;
        map[3][2] = 1;

        for (int i = 0; i < map.length; i++) {
            int[] arr = map[i];
            System.err.println(Arrays.toString(arr));
        }
        setWay(map, 1, 1);
        System.err.println("=======================");
        for (int i = 0; i < map.length; i++) {
            int[] arr = map[i];
            System.err.println(Arrays.toString(arr));
        }
    }

    /**
     * 走迷宫
     *
     * @param map 地图
     * @param i   起点所在的行
     * @param j   起点所在的列
     * @return 是否能走成功
     */
    public static boolean setWay(int[][] map, int i, int j) {
        // 已经到终点了
        if (map[6][5] == 2) {
            return true;
        }
        // 当前这个点还没走过
        if (map[i][j] == 0) {
            map[i][j] = 2;
            if (setWay(map, i + 1, j)) {
                return true;
            } else if (setWay(map, i, j + 1)) {
                return true;
            } else if (setWay(map, i - 1, j)) {
                return true;
            } else if (setWay(map, i, j - 1)) {
                return true;
            } else {
                map[i][j] = 3;
                return false;
            }
        }
        return false;
    }
}
