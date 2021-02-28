package hashtab;

import lombok.Data;

import java.util.Scanner;

/**
 * @author suchaobin
 * @description 哈希表
 * @date 2021/2/28 22:50
 **/
@Data
public class HashTab {
    private int maxSize;
    private EmpLinkedList[] empLinkedListArr;

    public HashTab(int maxSize) {
        this.maxSize = maxSize;
        // 注意，此时初始化后，数组的每个对象都是null，也需要进行初始化
        empLinkedListArr = new EmpLinkedList[maxSize];
        for (int i = 0; i < empLinkedListArr.length; i++) {
            empLinkedListArr[i] = new EmpLinkedList();
        }
    }

    private void add(Emp emp) {
        int index = emp.id % maxSize;
        empLinkedListArr[index].add(emp);
    }

    private void show() {
        for (int i = 0; i < empLinkedListArr.length; i++) {
            empLinkedListArr[i].show();
        }
    }

    @Data
    private static class EmpLinkedList {
        // 头节点
        private Emp head;

        public void add(Emp emp) {
            if (head == null) {
                head = emp;
                return;
            }
            // 获取最后一个节点
            Emp curEmp = head;
            while (curEmp.next != null) {
                curEmp = curEmp.next;
            }
            // 把最后一个节点的下一个节点指向添加的节点
            curEmp.next = emp;
        }

        public void show() {
            if (head == null) {
                System.err.println("当前链表为空~");
                return;
            }
            Emp curEmp = head;
            while (curEmp != null) {
                System.err.println(curEmp.toString());
                curEmp = curEmp.next;
            }
        }
    }

    @Data
    private static class Emp {
        private Integer id;
        private String name;
        private Emp next;

        public Emp(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Emp{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.err.println("add(a)");
        System.err.println("list(l)");
        System.err.println("exit(e)");
        HashTab hashTab = new HashTab(10);
        boolean flag = true;
        while (flag) {
            Scanner scanner = new Scanner(System.in);
            char c = scanner.nextLine().charAt(0);
            try {
                switch (c) {
                    case 'a':
                        System.err.println("请输入要添加的员工ID~");
                        int id = scanner.nextInt();
                        System.err.println("请输入要添加的员工姓名~");
                        String name = scanner.next();
                        hashTab.add(new Emp(id, name));
                        break;
                    case 'l':
                        hashTab.show();
                        break;
                    case 'e':
                        flag = false;
                        scanner.close();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        System.err.println("程序退出");
    }
}
