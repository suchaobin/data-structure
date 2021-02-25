package stack;

import lombok.Data;

import java.util.Scanner;

/**
 * @author suchaobin
 * @description 自定义实现栈
 * @date 2021/2/25 20:04
 **/
@Data
public class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top = -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.err.println("栈满，无法添加~");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("当前栈空~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("当前栈空~");
        }
        return stack[top];
    }

    public void showStack() {
        if (isEmpty()) {
            System.err.println("当前栈空~");
            return;
        }
        for (int i = 0; i <= top; i++) {
            System.out.printf("stack[%s]=%s\n", i, stack[i]);
        }
    }

    public static void main(String[] args) {
        System.err.println("push");
        System.err.println("pop");
        System.err.println("peek");
        System.err.println("show");
        System.err.println("exit");
        ArrayStack arrayStack = new ArrayStack(3);
        boolean flag = true;
        while (flag) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            try {
                switch (str) {
                    case "push":
                        System.err.println("请输入要添加的数据~");
                        arrayStack.push(scanner.nextInt());
                        break;
                    case "pop":
                        System.err.println(arrayStack.pop());
                        break;
                    case "peek":
                        System.err.println(arrayStack.peek());
                        break;
                    case "show":
                        arrayStack.showStack();
                        break;
                    case "exit":
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
