import java.util.*;
public class Main {
    public static void main(String[] args) {
        String[] x = {"11", "25", "+", "32", "49", "-", "*"};
        calc(x);
    }

    private static void calc(String[] symbols) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i].equals("+") || symbols[i].equals("-") || symbols[i].equals("*")) {
                int x2 = stack.pop().intValue();
                int x1 = stack.pop().intValue();
                if (symbols[i].equals("+")) {
                    stack.push(x1 + x2);
                } else if (symbols[i].equals("-")) {
                    stack.push(x1 - x2);
                } else {
                    stack.push(x1 * x2);
                }

            } else {
                stack.push(Integer.valueOf(symbols[i]));
            }
        }
        System.out.println(stack.pop());
    }
}