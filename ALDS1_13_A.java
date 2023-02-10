import java.util.*;
public class Main {
    private static final int N = 8;
    private boolean[][] grid = new boolean[N][N]; 
    private boolean[] row = new boolean[N];
    private boolean[] col = new boolean[N];
    private boolean[] dpos = new boolean[2*N-1];
    private boolean[] dneg = new boolean[2*N-1];

    public static void main(String args[]) {
        Main m = new Main();
        m.input();
        m.backTrack(0);
    }

    private boolean backTrack(int r) {
        if (r == N) {
            output();
            return true;
        }
        for (int c = 0; c < N; c++) {
            if (!row[r] && !col[c] && !dpos[r+c] && !dneg[r-c+N-1]) {
                update(r, c, true);
                if (backTrack(r + 1)) {
                    return true;
                }
                update(r, c, false);
            }
        }
        return false;
    }

    private void update(int r, int c, boolean val) {
        grid[r][c] = val;
        row[r] = val;
        col[c] = val;
        dpos[r+c] = val;
        dneg[r-c+N-1] = val;
    }

    private void input() {
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
    }

    private void output() {
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
