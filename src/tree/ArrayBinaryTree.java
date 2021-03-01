package tree;

/**
 * @author suchaobin
 * @description 顺序存储二叉树
 * @date 2021/3/1 20:53
 **/
public class ArrayBinaryTree {
    private int[] arr;

    /**
     * 前序打印
     */
    public void preOrderPrint() {
        preOrderPrint(0);
    }

    /**
     * 中序打印
     */
    public void infixOrderPrint() {
        infixOrderPrint(0);
    }

    /**
     * 后续打印
     */
    public void postOrderPrint() {
        postOrderPrint(0);
    }

    /**
     * 前序打印
     *
     * @param index
     */
    private void preOrderPrint(int index) {
        if (arr == null || arr.length == 0) {
            System.err.println("数组为空~");
            return;
        }
        System.err.println(arr[index]);
        // 打印该节点下的左节点
        // 如果该节点的下标是index，该节点的左子节点下标为index * 2 + 1
        if (index * 2 + 1 < arr.length) {
            preOrderPrint(index * 2 + 1);
        }
        // 打印该节点下的右节点
        // 如果该节点的下标是index，该节点的右子节点下标为index * 2 + 2
        if (index * 2 + 2 < arr.length) {
            preOrderPrint(index * 2 + 2);
        }
    }

    /**
     * 中序打印
     *
     * @param index
     */
    private void infixOrderPrint(int index) {
        if (arr == null || arr.length == 0) {
            System.err.println("数组为空~");
            return;
        }
        if (index * 2 + 1 < arr.length) {
            infixOrderPrint(index * 2 + 1);
        }
        System.err.println(arr[index]);
        if (index * 2 + 2 < arr.length) {
            infixOrderPrint(index * 2 + 2);
        }
    }

    /**
     * 后续打印
     *
     * @param index
     */
    private void postOrderPrint(int index) {
        if (arr == null || arr.length == 0) {
            System.err.println("数组为空~");
            return;
        }
        if (index * 2 + 1 < arr.length) {
            postOrderPrint(index * 2 + 1);
        }
        if (index * 2 + 2 < arr.length) {
            postOrderPrint(index * 2 + 2);
        }
        System.err.println(arr[index]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree();
        arrayBinaryTree.arr = arr;
        System.err.println("前序打印：");
        arrayBinaryTree.preOrderPrint();
        System.err.println("中序打印：");
        arrayBinaryTree.infixOrderPrint();
        System.err.println("后续打印：");
        arrayBinaryTree.postOrderPrint();
    }
}
