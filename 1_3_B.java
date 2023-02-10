import java.util.*;
import java.lang.Math;
public class Main {
    public static void main(String args[]) { 
        Scanner scan = new Scanner(System.in);
        String[] nq = scan.nextLine().split(" ");
        int n = Integer.valueOf(nq[0]);
        int q = Integer.valueOf(nq[1]);

        LinkedList<String> queue = new LinkedList<>();
        Map<String, Integer> nameToTime = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] nt = scan.nextLine().split(" ");
            queue.add(nt[0]);
            nameToTime.put(nt[0], Integer.valueOf(nt[1]));
        }

        roundRobin(queue, nameToTime, q);
    }

    private static void roundRobin(LinkedList<String> queue, Map<String, Integer> nameToTime, int q) {

        int totalTime = 0;
        while (!queue.isEmpty()) {

            String p = queue.remove();
            int time = nameToTime.get(p).intValue();
            if (time > q) {
                queue.add(p);
                nameToTime.put(p, time - q);
                totalTime += q;

            } else {
                nameToTime.put(p, 0);
                totalTime += time;
                System.out.println(p + " " + totalTime);
            }
        }
    }
}