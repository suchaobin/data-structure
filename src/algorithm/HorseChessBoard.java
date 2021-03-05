package algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author suchaobin
 * @description 马踏棋盘算法
 * @date 2021/3/5 23:40
 **/
public class HorseChessBoard {
    // 棋盘的列
    private static final int X = 8;
    // 棋盘的行
    private static final int Y = 8;
    // 马起始位置的行
    private static final int ROW = 1;
    // 马起始位置的列
    private static final int COLUMN = 1;
    // 棋盘
    private static final int[][] CHESSBOARD = new int[Y][X];
    // 表示棋盘的各个位置是否被访问过了
    private static final boolean VISITED[] = new boolean[X * Y];
    // 表示马踏棋盘所有位置都访问过了，成功过关
    private static boolean isFinished = false;

    /**
     * 获取马在当前位置，可以往哪些地方走
     *
     * @param curPoint 当前马的位置
     * @return 马下一步可以往哪走
     */
    public static List<Point> getNext(Point curPoint) {
        List<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        // 马每次最多只能走8个位置，对着8个位置进行判断，符合条件的加入集合返回
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }

        return ps;
    }

    /**
     * 马踏棋盘算法
     *
     * @param chessboard 奇葩
     * @param row        马当前所在的行
     * @param column     马当前所在的列
     * @param step       当前是第几步
     */
    public static void travelChessboard(int[][] chessboard, int row, int column, int step) {
        // 棋盘的这个位置是第几步走的
        chessboard[row][column] = step;
        // 把这个位置标记为已访问
        VISITED[row * X + column] = true;
        // 获取当前可以走哪些位置
        List<Point> ps = getNext(new Point(column, row));
        // 使用贪心算法优化，下一步的下一步步数越少越优
        ps = ps.stream().sorted(Comparator.comparing(tmp -> getNext(tmp).size())).collect(Collectors.toList());
        // 对所有可以走的位置进行遍历
        while (!ps.isEmpty()) {
            Point point = ps.remove(0);
            // 当前这个位置还没有被访问过
            if (!VISITED[point.y * X + point.x]) {
                travelChessboard(chessboard, point.y, point.x, step + 1);
            }
        }
        // 回溯走到这里，说明这个位置走不通
        if (step < X * Y && !isFinished) {
            chessboard[row][column] = 0;
            VISITED[row * X + column] = false;
        } else {
            isFinished = true;
        }
    }

    public static void main(String[] args) {
        System.err.println("游戏开始~");
        long s = System.currentTimeMillis();
        travelChessboard(CHESSBOARD, ROW - 1, COLUMN - 1, 1);
        long e = System.currentTimeMillis();
        System.err.println("耗时：" + (e - s) + "毫秒");
        // 打印结果
        System.err.println("游戏结果：");
        for (int i = 0; i < CHESSBOARD.length; i++) {
            System.err.println(Arrays.toString(CHESSBOARD[i]));
        }
    }
}
