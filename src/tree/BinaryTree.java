package tree;

import lombok.Data;

/**
 * @author suchaobin
 * @description 二叉树
 * @date 2021/3/1 19:12
 **/
@Data
public class BinaryTree {
    public Node root;

    /**
     * 删除节点
     *
     * @param id
     */
    private void delNode(int id) {
        if (root == null) {
            System.err.println("当前树空~");
            return;
        }
        if (root.id == id) {
            root = null;
            return;
        }
        root.delNode(id);
    }

    /**
     * 前序搜索
     *
     * @param id
     * @return
     */
    private Node preOrderSearch(int id) {
        if (root == null) {
            System.err.println("当前树空~");
            return null;
        }
        return root.preOrderSearch(id);
    }

    /**
     * 中序搜索
     *
     * @param id
     * @return
     */
    private Node infixOrderSearch(int id) {
        if (root == null) {
            System.err.println("当前树空~");
            return null;
        }
        return root.preOrderSearch(id);
    }

    /**
     * 后续搜索
     *
     * @param id
     * @return
     */
    private Node postOrderSearch(int id) {
        if (root == null) {
            System.err.println("当前树空~");
            return null;
        }
        return root.preOrderSearch(id);
    }

    /**
     * 前序打印
     */
    private void preOrderPrint() {
        if (root == null) {
            System.err.println("当前树空，无法遍历~");
            return;
        }
        root.preOrderPrint();
    }

    /**
     * 中续打印
     */
    private void infixOrderPrint() {
        if (root == null) {
            System.err.println("当前树空，无法遍历~");
            return;
        }
        root.infixOrderPrint();
    }

    /**
     * 后续打印
     */
    private void postOrderPrint() {
        if (root == null) {
            System.err.println("当前树空，无法遍历~");
            return;
        }
        root.postOrderPrint();
    }

    @Data
    private static class Node {
        private int id;
        private String name;
        private Node left;
        private Node right;

        public Node(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * 删除节点
         *
         * @param id
         */
        private void delNode(int id) {
            if (this.left != null && this.left.id == id) {
                this.left = null;
                return;
            }
            if (this.right != null && this.right.id == id) {
                this.right = null;
                return;
            }
            if (this.left != null) {
                this.left.delNode(id);
            }
            if (this.right != null) {
                this.right.delNode(id);
            }
        }

        /**
         * 前序搜索
         *
         * @param id
         * @return
         */
        private Node preOrderSearch(int id) {
            if (this.id == id) {
                return this;
            }
            Node resultNode = null;
            if (this.left != null) {
                resultNode = this.left.preOrderSearch(id);
            }
            if (resultNode != null) {
                return resultNode;
            }
            if (this.right != null) {
                resultNode = this.right.preOrderSearch(id);
            }
            return resultNode;
        }

        /**
         * 中序搜索
         *
         * @param id
         * @return
         */
        private Node infixOrderSearch(int id) {
            Node resNode = null;
            if (this.left != null) {
                resNode = this.left.infixOrderSearch(id);
                if (resNode != null) {
                    return resNode;
                }
            }
            if (this.id == id) {
                return this;
            }
            if (this.right != null) {
                resNode = this.right.infixOrderSearch(id);
            }
            return resNode;
        }

        /**
         * 后续搜索
         *
         * @param id
         * @return
         */
        private Node postOrderSearch(int id) {
            Node resNode = null;
            if (this.left != null) {
                resNode = this.left.postOrderSearch(id);
                if (resNode != null) {
                    return resNode;
                }
            }
            if (this.right != null) {
                resNode = this.right.postOrderSearch(id);
                if (resNode != null) {
                    return resNode;
                }
            }
            if (this.id == id) {
                return this;
            }
            return null;
        }

        /**
         * 前序打印
         */
        private void preOrderPrint() {
            System.err.println(this.toString());
            if (this.left != null) {
                this.left.preOrderPrint();
            }
            if (this.right != null) {
                this.right.preOrderPrint();
            }
        }

        /**
         * 中序打印
         */
        private void infixOrderPrint() {
            if (this.left != null) {
                this.left.infixOrderPrint();
            }
            System.err.println(this.toString());
            if (this.right != null) {
                this.right.infixOrderPrint();
            }
        }

        /**
         * 后续打印
         */
        private void postOrderPrint() {
            if (this.left != null) {
                this.left.postOrderPrint();
            }
            if (this.right != null) {
                this.right.postOrderPrint();
            }
            System.err.println(this.toString());
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
        Node root = new Node(1, "一号");
        Node node2 = new Node(2, "二号");
        Node node3 = new Node(3, "三号");
        Node node4 = new Node(4, "四号");
        Node node5 = new Node(5, "五号");

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = root;
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;

        System.err.println("前序打印：");
        binaryTree.preOrderPrint();

        System.err.println("中序打印：");
        binaryTree.infixOrderPrint();

        System.err.println("后续打印：");
        binaryTree.postOrderPrint();

        /*System.err.println("前序查找：");
        System.err.println(binaryTree.preOrderSearch(4));
        System.err.println(binaryTree.preOrderSearch(7));

        System.err.println("中序查找：");
        System.err.println(binaryTree.infixOrderSearch(4));
        System.err.println(binaryTree.infixOrderSearch(7));

        System.err.println("后序查找：");
        System.err.println(binaryTree.postOrderSearch(4));
        System.err.println(binaryTree.postOrderSearch(7));*/

        binaryTree.delNode(3);


        System.err.println("前序打印：");
        binaryTree.preOrderPrint();

        System.err.println("中序打印：");
        binaryTree.infixOrderPrint();

        System.err.println("后续打印：");
        binaryTree.postOrderPrint();

    }
}
