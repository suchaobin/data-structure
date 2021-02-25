package queue;

import lombok.Data;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author suchaobin
 * @description 数组队列，当队列达到最大长度时候，如果数据都已经全部取出则无法再添加数据
 * @date 2021/2/25 09:38
 **/
@Data
public class ArrayQueue {
    // 最大长度
    private int maxSize;
    // 队列头
    private int front;
    // 队列尾
    private int rear;
    // 数组
    private int[] dataArr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
        this.dataArr = new int[maxSize];
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public boolean isFull() {
        return this.rear == maxSize - 1;
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
        rear++;
        dataArr[rear] = value;
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
        front++;
        System.err.println(dataArr[front]);
    }

    /**
     * 打印队列所有数据
     */
    public void printQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据~~");
        }
        for (int i = front + 1; i < dataArr.length; i++) {
            System.err.print(dataArr[i] + " ");
        }
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
        ArrayQueue queue = new ArrayQueue(3);
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
