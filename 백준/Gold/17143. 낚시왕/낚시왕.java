import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static class Shark {
        int r, c, s, d, z;
        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Shark[][] grid = new Shark[R + 1][C + 1]; // 1-indexed

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); // 1:up, 2:down, 3:right, 4:left
            int z = Integer.parseInt(st.nextToken());
            grid[r][c] = new Shark(r, c, s, d, z);
        }

        long result = 0;

        // For each column where the fisherman moves
        for (int col = 1; col <= C; col++) {
            // 1) catch nearest shark in this column (smallest row)
            for (int row = 1; row <= R; row++) {
                if (grid[row][col] != null) {
                    result += grid[row][col].z;
                    grid[row][col] = null;
                    break;
                }
            }

            // 2) move sharks
            Shark[][] newGrid = new Shark[R + 1][C + 1];

            for (int r = 1; r <= R; r++) {
                for (int c = 1; c <= C; c++) {
                    if (grid[r][c] == null) continue;
                    Shark sh = grid[r][c];

                    int nr = sh.r;
                    int nc = sh.c;
                    int nd = sh.d;
                    int speed = sh.s;

                    // Optimize speed using cycle
                    if (nd == 1 || nd == 2) { // vertical
                        if (R > 1) speed = speed % ((R - 1) * 2);
                    } else { // horizontal
                        if (C > 1) speed = speed % ((C - 1) * 2);
                    }

                    // move step by step (speed <= 198 typically so it's fine)
                    for (int step = 0; step < speed; step++) {
                        if (nd == 1) { // up
                            if (nr == 1) { nd = 2; nr++; }
                            else nr--;
                        } else if (nd == 2) { // down
                            if (nr == R) { nd = 1; nr--; }
                            else nr++;
                        } else if (nd == 3) { // right
                            if (nc == C) { nd = 4; nc--; }
                            else nc++;
                        } else if (nd == 4) { // left
                            if (nc == 1) { nd = 3; nc++; }
                            else nc--;
                        }
                    }

                    // place into newGrid, resolving conflicts by size
                    if (newGrid[nr][nc] == null) {
                        newGrid[nr][nc] = new Shark(nr, nc, sh.s, nd, sh.z);
                    } else {
                        if (newGrid[nr][nc].z < sh.z) {
                            newGrid[nr][nc] = new Shark(nr, nc, sh.s, nd, sh.z);
                        }
                    }
                }
            }

            grid = newGrid; // update grid
        }

        System.out.println(result);
    }
}
