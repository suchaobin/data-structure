package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author suchaobin
 * @description 后缀表达式计算
 * @date 2021/2/25 21:49
 **/
public class SuffixCalculator {
    public static void main(String[] args) {
        String expression = "3+(3+6)*5-1";
        System.err.println(parseExpression(expression));
    }

    private static int parseExpression(String expression) {
        List<String> suffixExpressionList = getSuffixExpressionList(expression);
        Stack<Integer> stack = new Stack<>();
        for (String str : suffixExpressionList) {
            // 如果是数字就入栈
            if (str.matches("\\d+")) {
                stack.push(Integer.parseInt(str));
                continue;
            }
            // 否则就进行计算
            Integer num1 = stack.pop();
            Integer num2 = stack.pop();
            int result = calculator(num1, num2, str);
            stack.push(result);
        }
        return stack.pop();
    }

    /**
     * 将中缀表达式转成集合
     *
     * @param expression 中缀表达式
     * @return
     */
    public static List<String> getInfixExpressionList(String expression) {
        List<String> infixExpressionList = new ArrayList<>();
        int index = 0;
        char c;
        String str = "";
        do {
            c = expression.charAt(index);
            // 非数字
            if (c < 48 || c > 59) {
                infixExpressionList.add(String.valueOf(c));
            } else {
                // 可能是多位数，要进行拼接
                str += c;
                // 当前已在最后一个下标
                if (index + 1 >= expression.length()) {
                    infixExpressionList.add(str);
                } else if (expression.charAt(index + 1) < 48 || expression.charAt(index + 1) > 59) {
                    // 下一个不是数字了
                    infixExpressionList.add(str);
                    str = "";
                }
            }
            index++;
        } while (index < expression.length());
        return infixExpressionList;
    }

    /**
     * 获取后缀表达式，以list集合方式
     *
     * @param expression 中缀表达式
     * @return
     */
    public static List<String> getSuffixExpressionList(String expression) {
        List<String> infixExpressionList = getInfixExpressionList(expression);
        // 需要一个栈放结果，还有一个当符号栈，因为结果栈最后要逆序输出，所以可以直接考虑队列或者集合
        List<String> suffixExpressionList = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < infixExpressionList.size(); i++) {
            String item = infixExpressionList.get(i);
            // 当前下标的元素是数字，数字直接入结果结合中
            if (item.matches("\\d+")) {
                suffixExpressionList.add(item);
                continue;
            }
            // 符号栈是空的，直接入栈
            if (stack.isEmpty()) {
                stack.push(item);
            } else if (item.equals("(")) {
                // 左括号直接入栈
                stack.push(item);
            } else if (item.equals(")")) {
                // 右括号入栈，然后一直取出数据添加到结果集中，直接左括号出现为止
                while (true) {
                    String pop = stack.pop();
                    if (pop.equals("(")) {
                        break;
                    }
                    suffixExpressionList.add(pop);
                }
            }  else {
                /*
                 * 否则判定优先级，如果当前优先级比符号栈顶部的优先级要高，则取出符号栈顶部数据，并
                 * 压入结果集中，然后把当前符号压入符号栈中，否则直接压入符号栈中
                 */
                while (!stack.isEmpty() && getPriority(item) <= getPriority(stack.peek())) {
                    String pop = stack.pop();
                    suffixExpressionList.add(pop);
                }
                stack.push(item);
            }
        }
        // 符号栈为空才结束
        while (!stack.isEmpty()) {
            suffixExpressionList.add(stack.pop());
        }
        return suffixExpressionList;
    }

    /**
     * 获取优先级
     *
     * @param oper 操作运算符
     * @return
     */
    public static int getPriority(String oper) {
        switch (oper) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    public static int calculator(int num1, int num2, String oper) {
        switch (oper) {
            case "+":
                return num2 + num1;
            case "-":
                return num2 - num1;
            case "*":
                return num2 * num1;
            case "/":
                return num2 / num1;
            default:
                throw new RuntimeException("操作错误~");
        }
    }
}
