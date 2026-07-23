import java.util.*;

class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        
        char[][] map = new char[n][m];
        int startR = 0;
        int startC = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R') {
                    startR = i;
                    startC = j;
                }
            }
        }
        
        return bfs(startR, startC, map, n, m);
    }
    
    int bfs(int sr, int sc, char[][] map, int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        q.add(new int[] {sr, sc, 0});
        visited[sr][sc] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];
            
            if(map[r][c] == 'G') {
                return cnt;
            }
            
            for(int d = 0; d < 4; d++) {
                int nr = r;
                int nc = c;
                
                //벽 or 테두리 만날때까지 직진
                while(true) {
                    int nextR = nr + dr[d];
                    int nextC = nc + dc[d];
                    
                    //벽 or 테두리면 멈춤
                    if(nextR < 0 || nextR >= n || nextC < 0 || nextC >= m || map[nextR][nextC] == 'D') {
                        break;
                    }
                    
                    nr = nextR;
                    nc = nextC;
                }
                
                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc, cnt+1});
                }
            }
        }
        return -1;
    }
}
