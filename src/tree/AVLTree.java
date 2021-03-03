package tree;

import lombok.Data;

/**
 * @author suchaobin
 * @description 平衡二叉树
 * @date 2021/3/3 20:51
 **/
public class AVLTree {
    private Node root;

    /**
     * 中序打印
     */
    public void infixOrderPrint() {
        if (root == null) {
            System.err.println("当前树空~");
            return;
        }
        this.root.infixOrderPrint();
    }

    /**
     * 添加节点
     *
     * @param node 节点
     */
    public void addNode(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        root.addNode(node);
    }

    @Data
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        /**
         * 左旋
         */
        public void leftRotate() {
            // 先复制当前节点
            Node node = new Node(this.value);
            node.left = this.left;
            node.right = this.right.left;
            this.value = this.right.value;
            this.left = node;
            this.right = this.right.right;
        }

        /**
         * 右旋
         */
        public void rightRotate() {
            Node node = new Node(this.value);
            node.right = this.right;
            node.left = this.left.right;
            this.value = this.left.value;
            this.left = this.left.left;
            this.right = node;
        }


        /**
         * 添加节点
         *
         * @param node 节点
         */
        public void addNode(Node node) {
            if (this.value > node.value) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.addNode(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.addNode(node);
                }
            }
            // 每次添加完节点后，判断左右子树的高度，如果高度不满足二叉平衡树的条件，需要旋转
            // 除了判断当前节点的旋转外，还要考虑旋转后还是不平衡二叉树的情况
            if (node.getRightHeight() - node.getLeftHeight() > 1) {
                // 右节点不为空，并且当前右子节点的右子树高度小于右子节点的左子树高度
                if (this.right != null && this.right.getRightHeight() < this.right.getLeftHeight()) {
                    rightRotate();
                    leftRotate();
                } else {
                    leftRotate();
                }
                return;
            }
            if (node.getLeftHeight() - node.getRightHeight() > 1) {
                if (this.left != null && this.getLeftHeight() < this.getRightHeight()) {
                    leftRotate();
                    rightRotate();
                } else {
                    rightRotate();
                }
            }
        }

        /**
         * 获取左子树的高度
         *
         * @return 左子树的高度
         */
        public int getLeftHeight() {
            if (this.left == null) {
                return 0;
            }
            return this.left.getHeight();
        }

        /**
         * 获取右子树的高度
         *
         * @return 右子树的高度
         */
        public int getRightHeight() {
            if (this.right == null) {
                return 0;
            }
            return this.right.getHeight();
        }

        /**
         * 获取以当前节点为树根节点的高度
         *
         * @return
         */
        private int getHeight() {
            return Math.max(this.left == null ? 0 : this.left.getHeight(),
                    this.right == null ? 0 : this.right.getHeight()) + 1;
        }

        /**
         * 中序打印
         */
        public void infixOrderPrint() {
            if (this.left != null) {
                this.left.infixOrderPrint();
            }
            System.err.println(this);
            if (this.right != null) {
                this.right.infixOrderPrint();
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
