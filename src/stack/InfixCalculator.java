package stack;

import java.util.Stack;

/**
 * @author suchaobin
 * @description 中缀表达式计算
 * @date 2021/2/25 20:25
 **/
public class InfixCalculator {
    /*
     * 中缀表达式对人来说比较友好，但是对计算机来说复杂，因为每次碰到符号都要进行优先级的比较，所以用计算机来计算的话，
     * 是不考虑用中缀表达式来计算的。
     *
     * 1. 创建2个栈，一个用来存放数字，一个用来存放符号
     * 2. 对表达式遍历，数字就直接放入数栈中
     * 3. 如果是符号，则要进行判断
     *  3.1 如果符号栈为空，则直接放入符号栈中。
     *  3.2 如果符号栈非空，且当前符号的优先级更高，则直接放入符号栈中。
     *  3.3 如果符号栈非空，且符号栈栈顶的优先级更高，则需要和符号栈栈顶进行循环比较，如果符号栈栈顶的优先级更高，
     *      就要取出数栈中的顶部2个数字和符号栈顶部符号进行运算，得到结果后，把结果放入数栈中，并继续循环比较。直到
     *      符号栈为空或者当前符号优先级比栈顶的优先级更高后，把当前符号放入符号栈中。
     * 4. 表达式遍历完后，数栈和符号栈都有了数据，此时要进行进一步运算，也就是当符号栈为空的时候，数栈中有一个数，这
     *    个数也就是计算的结果。计算过程就是取出数栈中的顶部2个数字和符号栈顶部符号进行运算，得到结果后，把结果放入数栈中
     */
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
