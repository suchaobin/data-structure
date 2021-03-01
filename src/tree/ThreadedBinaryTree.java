package tree;

import lombok.Data;

/**
 * @author suchaobin
 * @description 线索化二叉树
 * @date 2021/3/1 21:15
 **/
@Data
public class ThreadedBinaryTree {
    // 根节点
    private Node root;
    // 当前的前驱节点（用于线索化）
    private Node preNode;

    /**
     * 中序打印
     */
    public void infixOrderPrint() {
        Node temp = root;
        while (temp != null) {
            // 找到当前要打印的第一个节点
            while (temp.leftType == 0) {
                temp = temp.left;
            }
            // 打印当前节点
            System.err.println(temp.toString());
            // 打印当前节点的后继节点
            while (temp.rightType == 1) {
                temp = temp.right;
                System.err.println(temp);
            }
            // 如果跳出了循环，说明此时的temp是一个非叶子节点，要手动把他替换成他的右子节点
            temp = temp.right;
        }
    }

    /**
     * 中序线索化
     */
    public void infixThreaded() {
        infixThreaded(root);
    }

    /**
     * 中序线索化
     *
     * @param node
     */
    private void infixThreaded(Node node) {
        // 当前节点为空
        if (node == null) {
            return;
        }
        // 向左递归线索化
        infixThreaded(node.left);
        // 线索化该节点
        // 设置前驱节点
        if (node.left == null) {
            node.left = preNode;
            node.leftType = 1;
        }
        // 设置后继节点，后继节点是回溯后，得到前驱节点来设置
        if (preNode != null && preNode.right == null) {
            preNode.right = node;
            preNode.rightType = 1;
        }
        // 每次线索化后，让当前节点称为前驱节点
        preNode = node;
        // 向右递归线索化
        infixThreaded(node.right);
    }

    @Data
    private static class Node {
        private int id;
        private String name;
        private Node left;
        private Node right;
        // 字节点的状态，0是非线索化，1是线索化
        private int leftType;
        private int rightType;

        public Node(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree();
        Node root = new Node(1, "jack");
        Node node1 = new Node(3, "tom");
        Node node2 = new Node(6, "mike");

        root.left = node1;
        root.right = node2;
        binaryTree.root = root;

        Node node3 = new Node(8, "林冲");
        Node node4 = new Node(10, "关胜");
        node1.left = node3;
        node1.right = node4;
        Node node5 = new Node(14, "jerry");
        node2.left = node5;

        System.out.println("---中序---");
        binaryTree.infixThreaded();

        System.out.println("node5.right = " + node5.right);

        binaryTree.infixOrderPrint();
    }
}
