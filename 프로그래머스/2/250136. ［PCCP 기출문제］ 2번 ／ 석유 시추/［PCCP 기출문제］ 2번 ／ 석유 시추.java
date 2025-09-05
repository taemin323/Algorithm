/**
* 1 <= n <= 500
* 1 <= m <= 500
* i+1, j+1 땅의 정보를 나타냄.
* 0이면 빈 땅, 1이면 석유
*/
import java.util.*;

class Solution {
    
    class Point {
        int r, c;
        
        Point() {
        }
        
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    int[][] map;
    int N;
    int M;
    boolean[][] visited;
    int[] line;
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    
    public int solution(int[][] land) {
        map = land;
        N = land.length;
        M = land[0].length;
        
        visited = new boolean[N][M];
        line = new int[M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(land[i][j] == 1 && !visited[i][j]) {
                    bfs(i,j);
                }
            }
        }

        int answer = 0;
        for(int l : line) answer = Math.max(answer, l);
        return answer;
    }
    
    public void bfs(int i, int j) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point (i, j));
        visited[i][j] = true;
        
        int qSize = 1;
        int start = j;
        int end = j;
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            int curR = cur.r;
            int curC = cur.c;
            
            start = Math.min(start, curC);
            end = Math.max(end, curC);
            
            for(int d = 0; d < dc.length; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                
                if(!visited[nr][nc] && map[nr][nc] != 0) {
                    visited[nr][nc] = true;
                    q.add(new Point (nr, nc));
                    qSize++;   
                }
            }
        }
        
        for(int k = start; k <= end; k++) {
            line[k] += qSize;
        }
    }
}