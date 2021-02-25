package sparsearray;

import java.util.Arrays;

/**
 * @author suchaobin
 * @description 稀疏数组
 * @date 2021/2/25 09:14
 **/
public class SparseArray {
    public static void main(String[] args) {
        // 棋盘数组，第2行第3列有白棋，第3行第4列有黑棋
        int[][] cheerArr = new int[9][9];
        cheerArr[1][2] = 1;
        cheerArr[2][3] = 2;
        for (int i = 0; i < cheerArr.length; i++) {
            System.err.println(Arrays.toString(cheerArr[i]));
        }

        System.err.println("=============================");

        // 空间浪费，进行压缩成稀疏数组
        // 获取棋盘数组有效数据数量
        int count = 0;
        for (int i = 0; i < cheerArr.length; i++) {
            for (int j = 0; j < cheerArr[i].length; j++) {
                if (cheerArr[i][j] != 0) {
                    count++;
                }
            }
        }
        // 创建稀疏数组，稀疏数组固定3列，行数是原数组有效数据+1，因为默认头数据要是记录整个原数组基本信息
        int[][] sparseArr = new int[count + 1][3];
        // 添加头数据
        sparseArr[0][0] = 9;
        sparseArr[0][1] = 9;
        sparseArr[0][2] = count;
        // 往稀疏数组里面添加数据
        int curRow = 1;
        for (int i = 0; i < cheerArr.length; i++) {
            for (int j = 0; j < cheerArr[i].length; j++) {
                if (cheerArr[i][j] != 0) {
                    sparseArr[curRow][0] = i;
                    sparseArr[curRow][1] = j;
                    sparseArr[curRow][2] = cheerArr[i][j];
                    curRow++;
                }
            }
        }
        for (int i = 0; i < sparseArr.length; i++) {
            System.err.println(Arrays.toString(sparseArr[i]));
        }

        System.err.println("=============================");

        // 将稀疏数组还原
        // 根据稀疏数组头部数据创建二维数组
        int row = sparseArr[0][0];
        int col = sparseArr[0][1];
        int[][] cheerArr2 = new int[row][col];
        for (int i = 1; i < sparseArr.length; i++) {
            int row2 = sparseArr[i][0];
            int col2 = sparseArr[i][1];
            cheerArr2[row2][col2] = sparseArr[i][2];
        }
        for (int i = 0; i < cheerArr2.length; i++) {
            System.err.println(Arrays.toString(cheerArr2[i]));
        }
    }
}
