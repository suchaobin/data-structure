package algorithm;

import lombok.Data;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 弗洛伊德算法
 * @date 2021/3/5 22:13
 **/
public class FloydAlgorithm {
    private static final int N = 65535;

    @Data
    private static class Graph {
        // 顶点
        private char[] vertexes;
        // 各个点之间的最短距离
        private int[][] dis;
        // 各个点之前的前驱节点
        private int[][] pre;

        public Graph(char[] vertexes, int[][] dis) {
            this.vertexes = vertexes;
            this.dis = dis;
            // 顶点数量
            int len = vertexes.length;
            pre = new int[len][len];
            for (int i = 0; i < len; i++) {
                Arrays.fill(pre[i], i);
            }
        }

        /**
         * 佛洛依德算法
         */
        public void floyd() {
            // k是中间节点
            for (int k = 0; k < this.vertexes.length; k++) {
                // i是出发节点
                for (int i = 0; i < this.vertexes.length; i++) {
                    // j是到达节点
                    for (int j = 0; j < this.vertexes.length; j++) {
                        int len = dis[i][k] + dis[k][j];
                        if (len < dis[i][j]) {
                            dis[i][j] = len;
                            pre[i][j] = pre[k][j];
                        }
                    }
                }
            }
        }

        public void show() {
            //顶点数组
            char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
            for (int k = 0; k < dis.length; k++) {
                for (int i = 0; i < dis.length; i++) {
                    System.out.print(vertex[pre[k][i]] + " ");
                }
                System.out.println();

                for (int i = 0; i < dis.length; i++) {
                    System.out.print("(" + vertex[k] + "到" + vertex[i] + "最短距离" + dis[k][i] + ") ");
                }
                System.out.println();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'}; //顶点数组
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        // 构建图对象
        Graph G = new Graph(vertex, matrix);
        // 调用佛洛依德算法
        G.floyd();
        // 打印结果
        G.show();
    }
}
