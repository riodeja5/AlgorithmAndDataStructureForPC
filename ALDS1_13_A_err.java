import java.util.*;
public class Main {
    private static final int N = 8;
    public static void main(String args[]) { 
        boolean[][] grid = new boolean[N][N]; 
        boolean[] row = new boolean[N];
        boolean[] col = new boolean[N];
        boolean[] dpos = new boolean[2*N-1];
        boolean[] dneg = new boolean[2*N-1];

        Scanner scan = new Scanner(System.in);
        int k = Integer.valueOf(scan.nextLine()).intValue();
        for (int i = 0; i < k; i++) {
            String[] sRC = scan.nextLine().split(" ");
            int r = Integer.valueOf(sRC[0]).intValue();
            int c = Integer.valueOf(sRC[1]).intValue();
            grid[r][c] = true;
            row[r] = true;
            col[c] = true;
            dpos[r+c] = true;
            dneg[r-c+N-1] = true;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (row[i]) continue;
                if (col[j]) continue;
                if (dpos[i+j]) continue;
                if (dneg[i-j+N-1]) continue;
                // 新たに配置
                grid[i][j] = true;
                row[i] = true;
                col[j] = true;
                dpos[i+j] = true;
                dneg[i-j+N-1] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j]) {
                    System.out.print("Q");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
