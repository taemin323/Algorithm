import java.util.*;

class Solution {
    static class Point {
        int r, c;
        
        public Point() {
        }
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    private static int N;
    private static int M;
    private static int[] dr = {0,0,-1,1};
    private static int[] dc = {-1,1,0,0};
    private static boolean[][] visited;
    private static int[][] dist;
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        if(maps[0][0] == 0) return -1;
        
        visited = new boolean[N][M];
        dist = new int[N][M];
        
        return bfs(0,0, maps);
    }
    
    private int bfs(int r, int c, int[][] maps) {
        Queue<Point> q = new LinkedList<>();
        visited[r][c] = true;
        dist[r][c] = 1;
        q.add(new Point(r, c));
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            int curR = cur.r;
            int curC = cur.c;
            
            if(curR == N-1 && curC == M-1) return dist[curR][curC];
            
            for(int d = 0; d < dc.length; d++){
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                
                if(!visited[nr][nc] && maps[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    dist[nr][nc] = dist[curR][curC] + 1;
                    q.add(new Point(nr, nc));
                }
            }
        }
        
        return -1;
    }
}