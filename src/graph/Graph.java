package graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author suchaobin
 * @description 图
 * @date 2021/3/3 21:23
 **/
@Data
public class Graph {
    // 存储定点的集合
    private List<String> vertexList;
    // 存储图对应的邻接矩阵
    private int[][] edges;
    // 表示边的数目
    private int numOfEdges;
    // 用于存储已访问的下标
    private boolean[] isVisited;

    public Graph(int size) {
        this.vertexList = new ArrayList<>(size);
        this.edges = new int[size][size];
        isVisited = new boolean[size];
        numOfEdges = 0;
    }

    /**
     * 添加节点
     *
     * @param vertex 节点
     */
    public void insertVertex(String vertex) {
        this.vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     顶点1的下标
     * @param v2     顶点2的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 获取顶点数量
     *
     * @return 顶点数量
     */
    public int getNumOfVertex() {
        return this.vertexList.size();
    }

    /**
     * 获取权值
     *
     * @param v1 顶点1
     * @param v2 顶点2
     * @return 两点边的权值
     */
    public int getWeight(int v1, int v2) {
        return this.edges[v1][v2];
    }

    /**
     * 获取边的数量
     *
     * @return 边的数量
     */
    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    /**
     * 打印图所对应的邻接矩阵
     */
    public void showGraph() {
        for (int i = 0; i < this.edges.length; i++) {
            System.err.println(Arrays.toString(this.edges[i]));
        }
    }

    /**
     * 得到第一个邻接节点的下标
     *
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据上一个节点的坐标来获取下一个邻接节点
     *
     * @param v1 上一个节点的横坐标
     * @param v2 上一个节点的纵坐标
     * @return 下一个邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历
     */
    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 深度优先遍历
     *
     * @param isVisited 存储是否访问的数组
     * @param i         当前深度遍历的下标
     */
    private void dfs(boolean[] isVisited, int i) {
        // 访问当前节点，并置为已访问
        System.err.println(vertexList.get(i) + "->");
        this.isVisited[i] = true;
        // 查找当前节点的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w >= 0) {
            // 当前这个节点还没有访问过
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w已经访问过了，获取w的下一个邻接节点
            w = getNextNeighbor(i, w);
        }
        // 不用对没找到邻接节点进行操作，因为递归会有回溯，越早递归，越晚回溯，所以这一次失败，必定是会先执行上一次的回溯
        // 也就满足了深度优先遍历的情况
    }

    /**
     * 广度优先遍历
     */
    private void bfs() {
        for (int i = 0; i < this.vertexList.size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    /**
     * 广度优先遍历
     *
     * @param isVisited 存储是否访问的数组
     * @param i         当前深度遍历的下标
     */
    private void bfs(boolean[] isVisited, int i) {
        // 队列，记录节点的访问顺序
        LinkedList<Integer> queue = new LinkedList<>();
        // 输出当前节点，标记为已访问
        System.err.println(this.vertexList.get(i) + "->");
        this.isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);
        // 从队列取出数据
        while (!queue.isEmpty()) {
            // 队列头节点对应的下标
            int u = queue.removeFirst();
            // 邻接节点
            int w = getFirstNeighbor(u);
            while (w >= 0) {
                if (!isVisited[w]) {
                    // 访问当前节点，并置为已访问
                    System.err.println(vertexList.get(w) + "->");
                    this.isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    public static void main(String[] args) {
        String vertexValue[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(vertexValue.length);
        // 插入节点
        for (int i = 0; i < vertexValue.length; i++) {
            graph.insertVertex(vertexValue[i]);
        }
        // 连边 AB BC AC BD BE
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        // 打印图的邻接矩阵
        graph.showGraph();
        // graph.dfs();
        graph.bfs();
    }
}
