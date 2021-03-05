package algorithm;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 普林姆算法
 * @date 2021/3/5 14:10
 **/
public class PrimAlgorithm {

    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};

        MGraph graph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        minTree.showGraph(graph);
        minTree.prim(graph, 1);
    }

}

//最小生成树
class MinTree {
    //创建
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //prim算法
    public void prim(MGraph graph, int v) {
        int visited[] = new int[graph.verxs];
        //标记已访问
        visited[v] = 1;
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        // 生成顶点-1条边
        for (int k = 1; k < graph.verxs; k++) {
            // 每次需要判断已访问节点和未访问节点的边是否是最小的，取一个最小的
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs;
    char[] data;
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
