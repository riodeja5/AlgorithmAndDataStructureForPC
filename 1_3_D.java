import java.util.*;
import java.lang.Math;
public class Main {
    public static void main(String args[]) { 
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        int total = 0;
        ArrayList<Integer> puddles = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        int curPuddle = 0;

        for (int i = 0; i < input.length(); i++) {
            char cin = input.charAt(i);
            if (cin == '/') {
                if (stack.empty()) {
                    continue;
                }
                curPuddle++;
                char cout = stack.pop();
                while (cout != '\\') {
                    curPuddle++;
                    cout = stack.pop();
                }

                // 未処理の水たまりがある場合は調査済みの水たまりはダミーデータをpush
                if (!stack.empty()) {
                    for (int j = 0; j < curPuddle + 1; j++) {
                        stack.push('-');
                    }
                } else {
                    if (curPuddle > 0) {
                        // 水たまりの区切りになるので値をリセットする
                        puddles.add(curPuddle);
                        total += (curPuddle);
                        curPuddle = 0;
                    }
                }

            } else if (cin == '\\') {
                stack.push(cin);
            } else {
                stack.push(cin);
            }
        }
        // 残りデータを保持
        puddles.add(curPuddle);
        total += (curPuddle);
        curPuddle = 0;

        // 結果を出力
        System.out.println(total);
        System.out.print(puddles.size());
        for (Integer puddle : puddles) {
            System.out.print(" " + puddle);
        }
    }
}