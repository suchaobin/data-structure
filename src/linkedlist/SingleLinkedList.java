package linkedlist;

import lombok.Data;

/**
 * @author suchaobin
 * @description 单向链表
 * @date 2021/2/25 10:46
 **/
@Data
public class SingleLinkedList {
    // 头节点
    private HeroNode head = new HeroNode(0, "头节点", "头节点");

    public void add(HeroNode node) {
        // 先找到链表的最后一个节点
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    public void addByOrder(HeroNode node) {
        // 先找到插入后的前一个节点
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.id > node.id) {
                break;
            }
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;
    }

    public void list() {
        HeroNode temp = head;
        while (true) {
            System.err.println(temp.toString());
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }

    public void remove(int id) {
        // 因为是单向链表，只能往后不能往前，所以必须是判断当前节点的下一个节点是否是要删除的节点来操作
        HeroNode temp = head;
        boolean ifRemove = false;
        while (true) {
            // 已经是最后一个节点了
            if (temp.next == null) {
                break;
            }
            // 找到符合的了
            if (temp.next.id == id) {
                temp.next = temp.next.next;
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
        HeroNode temp = head;
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
    public static class HeroNode {
        private int id;
        private String name;
        private String nickname;
        // 下一个节点
        private HeroNode next;

        public HeroNode(int id, String name, String nickname) {
            this.id = id;
            this.name = name;
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList.addByOrder(new HeroNode(3, "李逵", "黑旋风"));
        singleLinkedList.addByOrder(new HeroNode(2, "鲁智深", "花和尚"));
        singleLinkedList.list();
        // 修改
        System.err.println("======================");
        singleLinkedList.update(3, "小黑", "黑黑");
        singleLinkedList.list();
        // 删除
        System.err.println("======================");
        singleLinkedList.remove(2);
        singleLinkedList.list();
    }
}
