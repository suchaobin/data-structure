package tree;

import lombok.Data;

/**
 * @author suchaobin
 * @description 二叉排序树
 * @date 2021/3/2 23:44
 **/
public class BinarySortTree {
    private Node root;

    public void delNode(int value) {
        if (root == null) {
            return;
        }
        // 先找到要删除的节点
        Node targetNode = search(value);
        if (targetNode == null) {
            return;
        }
        // 要删除的是根节点，且当前树只有一个节点
        if (targetNode.value == root.value && root.left == null && root.right == null) {
            root = null;
            return;
        }
        // 找到父节点
        Node parentNode = searchParent(value);
        // 如果当前要删除的节点是叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            // 当前节点是父节点的左子节点
            if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            // 如果当前要删除的节点有2个子节点
            // 把该节点下的树看成一颗新的二叉排序树，从左子树中找一个最大值，或者右子树中找一个最小值移到这里即可
            targetNode.value = delRightTreeMin(targetNode.right);
        } else {
            // 当前要删除的节点只有一个子节点
            // 当前节点是父节点的左子节点
            if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                // 要删除的节点是子节点是左子节点
                if (targetNode.left != null) {
                    parentNode.left = targetNode.left;
                } else {
                    parentNode.left = targetNode.right;
                }
            } else {
                // 当前节点是父节点的右子节点
                // 要删除的节点是子节点是左子节点
                if (targetNode.left != null) {
                    parentNode.right = targetNode.left;
                } else {
                    parentNode.right = targetNode.right;
                }
            }
        }
    }

    /**
     * 删除右子树的最小值，并返回删除的值
     *
     * @param node 从哪个节点开始扫描
     * @return 删除的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        // 循环查找左节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 删除该节点
        delNode(target.value);
        return target.value;
    }


    public void addNode(Node node) {
        // 当前没有根节点
        if (root == null) {
            root = node;
            return;
        }
        root.addNode(node);
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * 中序打印
     */
    public void infixOrderPrint() {
        if (root == null) {
            System.err.println("当前树空~");
            return;
        }
        root.infixOrderPrint();
    }

    @Data
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node search(int value) {
            if (this.value == value) {
                return this;
            } else if (this.value > value) {
                if (this.left == null) {
                    return null;
                }
                return this.left.search(value);
            } else {
                if (this.right == null) {
                    return null;
                }
                return this.right.search(value);
            }
        }

        public Node searchParent(int value) {
            // 要找的节点是当前节点的左子节点
            if (this.left != null && this.left.value == value) {
                return this;
            }
            if (this.right != null && this.right.value == value) {
                // 要找的节点是当前节点的右子节点
                return this;
            }
            // 要找的节点的值比当前节点的值小，去当前节点的左子树中找
            if (this.value > value && this.left != null) {
                return this.left.searchParent(value);
            } else if (this.value <= value && this.right != null) {
                // 要找的节点的值比当前节点的值大，去当前节点的右子树中找
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }

        public void addNode(Node node) {
            // 当前节点的值比要添加的节点的值大，挂在当前节点的左边
            if (this.value > node.value) {
                // 当前节点的左子节点为空
                if (this.left == null) {
                    this.left = node;
                } else {
                    // 左子节点遍历下去
                    this.left.addNode(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.addNode(node);
                }
            }
        }

        // 中序打印
        private void infixOrderPrint() {
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

    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 11, 13, 4};
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.addNode(new Node(arr[i]));
        }

        // 中序打印输出
        binarySortTree.infixOrderPrint();
        // 删除节点
        binarySortTree.delNode(11);
        System.err.println("删除后");
        binarySortTree.infixOrderPrint();
    }
}
