package queue;

import lombok.Data;

import java.util.Scanner;

/**
 * @author suchaobin
 * @description 环形队列，解决数组复用问题
 * @date 2021/2/25 10:17
 **/
@Data
public class CircleArrayQueue {
    // 最大长度
    private int maxSize;
    // 队列头
    private int front;
    // 队列尾
    private int rear;
    // 数组
    private int[] dataArr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = 0;
        this.rear = 0;
        this.dataArr = new int[maxSize];
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 添加数据
     *
     * @param value 数据
     */
    public void add(int value) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法添加~~");
        }
        dataArr[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 从队列取数据
     *
     * @return
     */
    public void get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据~~");
        }
        System.err.println(dataArr[front]);
        front = (front + 1) % maxSize;
    }

    /**
     * 打印队列所有数据
     */
    public void printQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据~~");
        }
        for (int i = front; i < front + this.size(); i++) {
            System.err.printf("arr[%s]=%s\n", i % maxSize, dataArr[i % maxSize]);
        }
    }

    private int size() {
        // rear - front 就可以取出长度，但是因为是环形的，所以可能得到负数，所以此时加一个maxSize再模以maxSize
        return  (rear - front + maxSize) % maxSize;
    }

    /**
     * 打印队列头部数据
     */
    public void printHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据~~");
        }
        System.err.println(dataArr[front]);
    }

    public static void main(String[] args) {
        System.err.println("add(a)");
        System.err.println("get(g)");
        System.err.println("head(h)");
        System.err.println("list(l)");
        System.err.println("exit(e)");
        CircleArrayQueue queue = new CircleArrayQueue(4);
        boolean flag = true;
        while (flag) {
            Scanner scanner = new Scanner(System.in);
            char c = scanner.nextLine().charAt(0);
            try {
                switch (c) {
                    case 'a':
                        System.err.println("请输入要添加的数据~");
                        queue.add(scanner.nextInt());
                        break;
                    case 'g':
                        queue.get();
                        break;
                    case 'h':
                        queue.printHead();
                        break;
                    case 'l':
                        queue.printQueue();
                        break;
                    case 'e':
                        flag = false;
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
