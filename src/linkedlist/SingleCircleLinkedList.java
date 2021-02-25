package linkedlist;

import lombok.Data;

/**
 * @author suchaobin
 * @description 单向循环列表
 * @date 2021/2/25 11:44
 **/
@Data
public class SingleCircleLinkedList {
    private PersonNode first = null;

    public void add(int num) {
        if (num <= 0) {
            System.out.println("参数不合法");
            return;
        }
        PersonNode curNode = null;
        for (int i = 1; i <= num; i++) {
            PersonNode personNode = new PersonNode(i);
            if (i == 1) {
                first = personNode;
                // 自成环
                first.next = first;
                curNode = first;
                continue;
            }
            curNode.next = personNode;
            personNode.next = first;
            curNode = personNode;
        }
    }

    /**
     * 约瑟夫问题
     *
     * @param n n个人围成圈
     * @param k 从第k个人开始报数
     * @param m 每次数到m的人出列
     */
    public void joseph(int n, int k, int m) {
        // n个人围成圈
        add(n);
        /*
         因为first节点是要出圈的人，所以定义一个helper节点，helper节点是first的前一个节点，
         当helper节点和first节点在一起的时候，就是只剩一个人
         */
        PersonNode helper = first;
        while (helper.next != first) {
            helper = helper.next;
        }
        // 从第k个人开始，因为第一个人也要报数，所以循环k-1次
        for (int i = 0; i < k - 1; i++) {
            first = first.next;
            helper = helper.next;
        }
        // 剩余超过2个人
        while (first != helper) {
            // 每次数到m的人出圈，因为第一个人也要报数，所以循环m-1次
            for (int i = 0; i < m - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            System.err.println("出圈的人是：" + first.id);
            // 出圈后，first要后移，helper就紧跟first后面
            first = first.next;
            helper.next = first;
        }
        System.err.println("最后的人是：" + first.id);
    }

    @Data
    public static class PersonNode {
        private int id;
        private PersonNode next;

        public PersonNode(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        SingleCircleLinkedList singleCircleLinkedList = new SingleCircleLinkedList();
        singleCircleLinkedList.add(5);
        singleCircleLinkedList.joseph(5, 1, 2);
    }
}
