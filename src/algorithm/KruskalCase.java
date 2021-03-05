package algorithm;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suchaobin
 * @description 克鲁斯卡尔算法
 * @date 2021/3/5 14:17
 **/
@Data
public class KruskalCase {
    // 边的数量
    private int edgeNum;
    // 顶点数组
    private char[] vertexes;
    // 邻接矩阵
    private int[][] matrix;
    // 使用INF表示两个顶点之间不能直接连通
    private static final Integer INF = Integer.MAX_VALUE;

    public KruskalCase(char[] vertexes, int[][] matrix) {
        // 初始化顶点的个数
        int length = vertexes.length;
        // 初始化顶点
        this.vertexes = new char[length];
        for (int i = 0; i < vertexes.length; i++) {
            this.vertexes[i] = vertexes[i];
        }
        // 初始化邻接矩阵
        this.matrix = new int[length][length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        // 统计边
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                if (this.matrix[i][j] != INF) {
                    count++;
                }
            }
        }
        this.edgeNum = count;
    }

    public void kruskal() {
        // 用于保存 已有最小生成树的 每个顶点的终点
        int[] ends = new int[this.vertexes.length];
        // 创建结果集，保存最后的最小生成树
        List<EData> res = new ArrayList<>();
        // 获取所有的边，并对边进行排序
        EData[] edges = getEdges();
        sortEdges(edges);
        // 遍历edges数组，将边添加到最小生成树中，并判断有没有形成回路，如果没有，就加入res中
        for (int i = 0; i < edges.length; i++) {
            EData edge = edges[i];
            // 顶点1的下标
            int p1 = getPosition(edge.getStart());
            // 顶点2下标
            int p2 = getPosition(edge.getEnd());
            // 判断两个顶点的终点是否相同
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) {
                // 没有形成回路，将边加入结果中，并且此时m的终点变成n
                ends[m] = n;
                res.add(edge);
            }
        }
        // 打印最小生成树
        System.err.println("最小生成树:");
        res.forEach(System.err::println);
    }

    /**
     * 打印邻接矩阵
     */
    private void print() {
        System.err.println("邻接矩阵为:");
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                System.err.printf("%12d", matrix[i][j]);
            }
            System.err.println();
        }
    }

    /**
     * 对边进行排序
     *
     * @param edges 边的数组
     */
    private static void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 获取下标为i的顶点对应的终点下标
     *
     * @param ends 记录了各个顶点对应的终点，在遍历过程中不断形成
     * @param i    顶点的下标
     * @return 终点下标
     */
    private int getEnd(int[] ends, int i) {
        // 这里的循环举例：1的终点是2，2的终点是3，此时1的终点应该是3，每次循环就是把这个终点当新的起点来看
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * 获取顶点下标
     *
     * @param c 要查找的顶点
     * @return 顶点下标，没找到返回-1
     */
    private int getPosition(char c) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private EData[] getEdges() {
        // 边数组
        EData[] edges = new EData[this.edgeNum];
        // 边数组要插入的下标
        int index = 0;
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = i + 1; j < this.matrix[i].length; j++) {
                if (this.matrix[i][j] != INF) {
                    EData eData = new EData(this.vertexes[i], this.vertexes[j], this.matrix[i][j]);
                    edges[index] = eData;
                    index++;
                }
            }
        }
        return edges;
    }

    /**
     * 对应两个顶点的边
     */
    @Data
    private static class EData {
        // 边的起点
        private char start;
        // 边的终点
        private char end;
        // 权值，表示边长
        private int weight;

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EData{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        KruskalCase kruskalCase = new KruskalCase(vertexes, matrix);
        // 输出邻接矩阵
        kruskalCase.print();
        // 打印边
        kruskalCase.kruskal();
    }
}
