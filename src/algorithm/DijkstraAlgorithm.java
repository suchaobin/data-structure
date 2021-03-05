package algorithm;

import lombok.Data;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 迪杰斯特拉算法
 * @date 2021/3/5 16:15
 **/
public class DijkstraAlgorithm {
    // 表示不可以连接
    private static final int N = 65535;

    @Data
    private static class Graph {
        // 顶点数组
        private char[] vertexes;
        // 邻接矩阵
        private int[][] matrix;
        // 已经访问过的顶点数据
        private VisitedVertex visitedVertex;

        public Graph(char[] vertexes, int[][] matrix) {
            this.vertexes = vertexes;
            this.matrix = matrix;
        }

        public void showGraph() {
            for (int i = 0; i < matrix.length; i++) {
                System.err.println(Arrays.toString(matrix[i]));
            }
        }

        public void showDijkstra() {
            this.visitedVertex.showDijkstra();
        }

        /**
         * 迪杰斯特拉算法实现
         *
         * @param index 出发顶点下标
         */
        public void dsj(int index) {
            this.visitedVertex = new VisitedVertex(this.vertexes.length, index);
            // 更新index顶点到其他周围顶点的距离和前驱顶点
            update(index);
            // 因为初始化后，出发顶点已经被访问，所以从1开始
            for (int i = 1; i < this.vertexes.length; i++) {
                // 选择并返回新的访问顶点
                index = this.visitedVertex.updateArr();
                update(index);
            }
        }

        /**
         * 更新index顶点到其他顶点的距离，并且更新其他顶点的前驱节点
         *
         * @param index 顶点下标
         */
        private void update(int index) {
            int len = 0;
            // 遍历第matrix[index]行
            for (int i = 0; i < this.matrix[index].length; i++) {
                // 新的距离是上一段距离+这一段距离
                len = this.visitedVertex.getDis(index) + matrix[index][i];
                // 如果当前顶点没有访问过，并且新计算的距离更小，就要更新数据
                if (!this.visitedVertex.in(i) && len < this.visitedVertex.getDis(i)) {
                    // 更新前驱节点
                    this.visitedVertex.updatePre(i, index);
                    // 更新出发顶点到i顶点的距离
                    this.visitedVertex.updateDis(i, len);
                }
            }
        }
    }

    /**
     * 已经访问过的顶点数据
     */
    @Data
    private static class VisitedVertex {
        // 记录各个顶点是否已经访问过，0是未访问，1是已访问
        private int[] already;
        // 每个下标对应的值为前一个顶点下标，会动态更新
        private int[] preVisited;
        // 记录出发顶点到其他所有顶点的举例
        public int[] dis;

        public VisitedVertex(int length, int index) {
            this.already = new int[length];
            this.preVisited = new int[length];
            this.dis = new int[length];
            // 初始化dis数组
            Arrays.fill(dis, N);
            // 出发顶点到出发顶点距离是0
            this.dis[index] = 0;
            // 把出发顶点置为已访问
            this.already[index] = 1;
        }

        /**
         * 判断index顶点是否被访问过
         *
         * @param index 顶点下标
         * @return 是否被访问过
         */
        public boolean in(int index) {
            return already[index] == 1;
        }

        /**
         * 更新出发顶点到index顶点的距离
         *
         * @param index 顶点下标
         * @param dis   距离
         */
        public void updateDis(int index, int dis) {
            this.dis[index] = dis;
        }

        /**
         * 更新前驱节点下标，把index顶点的前驱节点下标置为preIndex
         *
         * @param index    顶点下标
         * @param preIndex 前驱节点的下标
         */
        public void updatePre(int index, int preIndex) {
            this.preVisited[index] = preIndex;
        }

        /**
         * 获取出发顶点到index顶点的距离
         *
         * @param index 顶点下标
         * @return 距离
         */
        public int getDis(int index) {
            return this.dis[index];
        }

        /**
         * 继续选择并返回新的访问节点，比如这里的G走完后，就是A作为新的访问节点
         *
         * @return 新的访问节点下标
         */
        public int updateArr() {
            int min = N;
            int index = 0;
            for (int i = 0; i < this.already.length; i++) {
                if (this.already[i] == 0 && this.dis[i] < min) {
                    min = this.dis[i];
                    index = i;
                }
            }
            // 标记为已访问
            this.already[index] = 1;
            return index;
        }

        private void showDijkstra() {
            System.err.println("already = " + Arrays.toString(already));
            System.err.println("preVisited = " + Arrays.toString(preVisited));
            System.err.println("dis = " + Arrays.toString(dis));
        }
    }

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        // 创建图
        Graph graph = new Graph(vertexes, matrix);
        // 打印图的邻接矩阵
        graph.showGraph();
        // 从G点出发
        graph.dsj(6);
        // 打印结果
        graph.showDijkstra();
    }
}
