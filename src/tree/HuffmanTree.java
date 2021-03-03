package tree;

import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author suchaobin
 * @description 赫夫曼树
 * @date 2021/3/2 20:45
 **/
public class HuffmanTree {
    private Node root;

    public HuffmanTree(Node root) {
        this.root = root;
    }

    /**
     * 前序打印
     */
    public void preOrderPrint() {
        if (root == null) {
            System.err.println("当前树空，无法打印~");
            return;
        }
        this.root.preOrderPrint();
    }

    /**
     * 创建一个赫夫曼树，并返回当前树的根节点
     *
     * @param nodes 创建树的节点
     * @return 当前树的根节点
     */
    private static Node creatHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 进行排序，使得nodes是从小到大排序
            nodes = nodes.stream().sorted(Comparator.comparing(Node::getValue)).collect(Collectors.toList());
            // 取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一个新的二叉树，他们的节点没有data，只有权值
            Node parent = new Node(null, leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 把处理过的数据从集合中删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 把生成的父节点添加到集合中
            nodes.add(parent);
        }
        // 最后集合中只会剩下一个根节点
        return nodes.get(0);
    }

    /**
     * 将byte数组转成一个Node集合，每个Node表示什么字符，出现了多少次
     *
     * @param bytes 需要处理的byte数组
     * @return
     */
    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        // 先把字符和出现的次数存在一个map中，每次出现就对map的值+1
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, ++count);
            }
        }
        // 把map中的数据放到要返回的集合中
        Set<Map.Entry<Byte, Integer>> entries = map.entrySet();
        for (Map.Entry<Byte, Integer> entry : entries) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    @Data
    private static class Node {
        // 数据
        private Byte data;
        // 出现的次数
        private int value;
        private Node left;
        private Node right;

        public Node(Byte data, int value) {
            this.data = data;
            this.value = value;
        }

        /**
         * 前序打印
         */
        public void preOrderPrint() {
            System.err.println(this);
            if (this.left != null) {
                this.left.preOrderPrint();
            }
            if (this.right != null) {
                this.right.preOrderPrint();
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    /**
     * 赫夫曼编码
     */
    private static class HuffmanCode {
        // 赫夫曼编码表
        private static Map<Byte, String> huffmanCodes = new HashMap<>();
        // 生成赫夫曼编码表需要拼接路径，定义一个StringBuilder存储某个叶子节点的路径
        private static StringBuilder stringBuilder = new StringBuilder();

        /**
         * 生成赫夫曼编码
         *
         * @param root 赫夫曼树的根节点
         */
        private static Map<Byte, String> getCodes(Node root) {
            getCodes(root, "", stringBuilder);
            return huffmanCodes;
        }

        /**
         * 将传入的node节点下的所有叶子节点都生成赫夫曼编码，并存入huffmanCodes中
         *
         * @param node          传入的节点
         * @param code          路径：左子节点是0，右子节点是1
         * @param stringBuilder 用来拼接路径
         */
        private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
            // 节点为空不处理
            if (node == null) {
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
            stringBuilder2.append(code);
            // 判断当前节点是什么节点
            // 如果当前节点是非叶子节点，就向两边递归生成赫夫曼编码
            if (node.data == null) {
                // 向左拼接
                if (node.left != null) {
                    getCodes(node.left, "0", stringBuilder2);
                }
                // 向右拼接
                if (node.right != null) {
                    getCodes(node.right, "1", stringBuilder2);
                }
            } else {// 当前节点是叶子节点，把该节点的赫夫曼编码保存起来
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }

        public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bytes) {
                stringBuilder.append(huffmanCodes.get(b));
            }
            String str = stringBuilder.toString();
            // 创建一个byte数组来存放压缩后的str数据
            int length;
            if (str.length() % 8 == 0) {
                length = str.length() / 8;
            } else {
                length = str.length() / 8 + 1;
            }
            /**
             * 因为直接通过赫夫曼编码拼凑成的字符串都是由0和1组成，所以我们可以每8位看成一个byte数据，
             * 将原先的字符串转成一个新的更小的byte数组，反正只要不影响我们的数据还原，压缩是越小越好
             * 多申请一个空间是专门用来存放最后一位解压时候需要补0的个数
             */
            byte[] huffmanCodeBytes = new byte[length + 1];
            // 字节数组创建之后，将对应的字符串按照8位转成一个字节
            int index = 0;
            // 如果最后一次是00110010、0110等情况，需要记录前面有多少个0
            int zeroCount = 0;
            for (int i = 0; i < str.length(); i += 8) {
                // 截取到的下标，不能超过字符串长度，所以取小
                int next = Math.min(i + 8, str.length());
                String strByte = str.substring(i, next);
                if (i + 8 >= str.length()) {
                    strByte = str.substring(i);
                    // 如果最后一次是00110010、0110等情况，需要记录前面有多少个0
                    for (int j = 0; j < strByte.length(); j++) {
                        if (strByte.length() == 1) {
                            break;
                        }
                        if (strByte.charAt(j) == '0') {
                            zeroCount++;
                        } else {
                            break;
                        }
                    }
                }
                // 获取了二进制数，并转为10进制，存放到huffmanCodeBytes中
                huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
                index++;
            }
            // 跳出循环后，把需要补0的次数放到数组最后一个位置上
            huffmanCodeBytes[huffmanCodeBytes.length - 1] = (byte) zeroCount;
            return huffmanCodeBytes;
        }
    }

    /**
     * 赫夫曼压缩
     *
     * @param bytes
     * @return
     */
    private static byte[] huffmanZip(byte[] bytes) {
        // 获取所有原始的节点
        List<Node> nodes = getNodes(bytes);
        // 生成一颗赫夫曼树
        Node root = creatHuffmanTree(nodes);
        HuffmanTree huffmanTree = new HuffmanTree(root);
        // 前序打印
        // huffmanTree.preOrderPrint();
        // 生成赫夫曼编码
        Map<Byte, String> codes = HuffmanCode.getCodes(root);
        // System.err.println(codes);
        // 获取压缩后的赫夫曼编码byte数组
        return HuffmanCode.zip(bytes, codes);
    }

    /**
     * 将当前byte转成二进制字符串
     *
     * @param flag      是否需要补高位
     * @param b         byte数据
     * @param zeroCount 最后一位需要补0的个数
     * @return
     */
    private static String byteToBinaryString(boolean flag, byte b, int zeroCount) {
        // 将b转成int
        int temp = b;
        if (flag) {
            // 按位或 1 0000 0000 | 0000 0001 = 1 0000 0001
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        }
        for (int i = 0; i < zeroCount; i++) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * 赫夫曼解压
     *
     * @param huffmanBytes 赫夫曼压缩过后的byte数组
     * @param huffmanCodes 赫夫曼编码表
     * @return 解压过后的byte数组
     */
    private static byte[] decode(byte[] huffmanBytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        int zeroCount = huffmanBytes[huffmanBytes.length - 1];
        // 只遍历到倒数第二个元素，最后一位放的是要补0的次数
        for (int i = 0; i < huffmanBytes.length - 1; i++) {
            // 判断是否是倒数第二个元素，只有倒数第二位元素不用进行 按位或
            boolean flag = i != huffmanBytes.length - 2;
            stringBuilder.append(byteToBinaryString(flag, huffmanBytes[i], zeroCount));
        }
        // 此时已经获取了赫夫曼路径拼接的字符串，只需要进行查找还原即可
        Map<String, Byte> map = new HashMap<>();
        // 把赫夫曼编码map反过来，方便接下来的查找
        huffmanCodes.forEach((k, v) -> map.put(v, k));
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        // 将集合转成byte数组返回
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    public static void main(String[] args) {
        String msg = "i like like like java do you like a java";

        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        System.err.println("压缩前长度:" + bytes.length);

        byte[] huffmanBytes = huffmanZip(bytes);
        System.err.println("压缩后长度:" + huffmanBytes.length);

        byte[] decode = decode(huffmanBytes, HuffmanCode.huffmanCodes);
        System.err.println("解压后长度:" + decode.length);

        System.err.println("解压后数据:" + new String(decode));
    }
}
