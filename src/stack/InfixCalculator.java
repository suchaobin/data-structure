package stack;

import java.util.Stack;

/**
 * @author suchaobin
 * @description 中缀表达式计算
 * @date 2021/2/25 20:25
 **/
public class InfixCalculator {
    public static void main(String[] args) {
        String expression = "6666-138*24+9999/99";
        System.err.println(parseExpression(expression));
    }

    private static int parseExpression(String expression) {
        // 数栈
        Stack<Integer> numStack = new Stack<>();
        // 符号栈
        Stack<Integer> operStack = new Stack<>();
        int index = 0;
        String str = "";
        while (true) {
            if (index >= expression.length()) {
                break;
            }
            char c = expression.charAt(index);
            // 48-59之间是数字，数字就入数栈中
            if (c >= 48 && c <= 59) {
                str += c;
                // 当前下标已经到最后了
                if (index + 1 >= expression.length()) {
                    numStack.push(Integer.parseInt(str));
                } else if (expression.charAt(index + 1) < 48 || expression.charAt(index + 1) > 59) {
                    // 下一个不是数字了
                    numStack.push(Integer.parseInt(str));
                    str = "";
                }
            } else if (operStack.isEmpty()) {
                // 如果是运算符，且符号栈是空的，直接入栈
                operStack.push((int) c);
            } else if (getPriority(c) > getPriority(operStack.peek())) {
                operStack.push((int) c);
            } else {
                /*
                 * 如果是运算符，且符号栈不为空，此时要循环比对栈顶符号的优先级，如果比栈顶优先级大
                 * 则入栈，否则取出符号栈栈顶符号和数栈中顶部2个数字进行计算，并将结果压入数栈中
                 */
                do {
                    Integer num1 = numStack.pop();
                    Integer num2 = numStack.pop();
                    Integer oper = operStack.pop();
                    int result = calculator(num1, num2, oper);
                    numStack.push(result);
                } while (!operStack.isEmpty() && getPriority(c) <= getPriority(operStack.peek()));
                operStack.push((int) c);
            }
            index++;
        }
        // 循环计算结果
        while (!operStack.isEmpty()) {
            Integer num1 = numStack.pop();
            Integer num2 = numStack.pop();
            Integer oper = operStack.pop();
            int result = calculator(num1, num2, oper);
            numStack.push(result);
        }
        return numStack.pop();
    }

    /**
     * 获取优先级
     *
     * @param oper 操作运算符
     * @return
     */
    public static int getPriority(int oper) {
        switch (oper) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    public static int calculator(int num1, int num2, int oper) {
        switch (oper) {
            case '+':
                return num2 + num1;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
            default:
                throw new RuntimeException("操作错误~");
        }
    }
}
