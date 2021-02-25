package linkedlist;

import lombok.Data;

/**
 * @author suchaobin
 * @description 双向循环列表
 * @date 2021/2/25 11:21
 **/
@Data
public class DoubleLinkedList {
    // 头节点
    private Node head = new Node(0, "头节点", "头节点");

    public void add(Node node) {
        // 先找到链表的最后一个节点
        DoubleLinkedList.Node temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    public void addByOrder(Node node) {
        // 先找到插入后的前一个节点
        Node temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.id > node.id) {
                break;
            }
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next.pre = node;
        }
        node.next = temp.next;
        temp.next = node;
        node.pre = temp;
    }

    public void list() {
        Node temp = head;
        while (true) {
            System.err.println(temp.toString());
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }

    public void remove(int id) {
        // 双向链表可以自删除，所以找到要删除的当前节点来操作
        Node temp = head.next;
        boolean ifRemove = false;
        while (true) {
            // 已经是最后一个节点了
            if (temp == null) {
                break;
            }
            // 找到符合的了
            if (temp.id == id) {
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                temp.pre.next = temp.next;
                ifRemove = true;
                break;
            }
            temp = temp.next;
        }
        // 删除失败
        if (!ifRemove) {
            System.err.println("删除失败，未找到id=" + id + "的节点");
        }
    }

    public void update(int id, String name, String nickname) {
        Node temp = head;
        boolean ifUpdate = false;
        while (true) {
            // 已经是最后一个节点了
            if (temp.next == null) {
                break;
            }
            // 找到符合的了
            if (temp.next.id == id) {
                temp.next.name = name;
                temp.next.nickname = nickname;
                ifUpdate = true;
                break;
            }
            temp = temp.next;
        }
        // 修改失败
        if (!ifUpdate) {
            System.err.println("修改失败，未找到id=" + id + "的节点");
        }
    }

    @Data
    public static class Node {
        private int id;
        private String name;
        private String nickname;
        // 上一个节点
        private Node pre;
        // 下一个节点
        private Node next;

        public Node(int id, String name, String nickname) {
            this.id = id;
            this.name = name;
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList DoubleLinkedList = new DoubleLinkedList();
        DoubleLinkedList.addByOrder(new DoubleLinkedList.Node(1, "宋江", "及时雨"));
        DoubleLinkedList.addByOrder(new DoubleLinkedList.Node(3, "李逵", "黑旋风"));
        DoubleLinkedList.addByOrder(new DoubleLinkedList.Node(2, "鲁智深", "花和尚"));
        DoubleLinkedList.list();
        // 修改
        System.err.println("======================");
        DoubleLinkedList.update(3, "小黑", "黑黑");
        DoubleLinkedList.list();
        // 删除
        System.err.println("======================");
        DoubleLinkedList.remove(2);
        DoubleLinkedList.remove(3);
        DoubleLinkedList.remove(1);
        DoubleLinkedList.list();
    }
}
