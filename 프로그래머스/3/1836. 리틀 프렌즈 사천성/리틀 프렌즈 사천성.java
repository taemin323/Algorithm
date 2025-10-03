import java.util.*;

class Solution {
    class Point {
        int r, c;
        
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    private int[] dr = {-1,1,0,0};
    private int[] dc = {0,0,-1,1};
    
    public String solution(int m, int n, String[] board) {
        // 각 알파벳 위치 기록
        Point[] p1 = new Point[26];
        Point[] p2 = new Point[26];
        boolean[] alive = new boolean[26];
        
        char[][] map = new char[m][n];
        for(int i = 0; i < m; i++) {
            String str = board[i];
            for(int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                char c = map[i][j];
                
                if(c >= 'A' && c <= 'Z') {
                    int idx = c - 'A';
                    if(p1[idx] == null) p1[idx] = new Point(i,j);
                    else p2[idx] = new Point(i,j);
                    alive[idx] = true;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(true) {
            boolean flag = false;
            
            for(int c = 0; c < 26; c++) {
                if(!alive[c]) continue;
                Point a = p1[c];
                Point b = p2[c];
                    
                if(bfs(map, a.r, a.c, b.r, b.c)) {
                    // 지우고 결과 추가
                    map[a.r][a.c] = '.';
                    map[b.r][b.c] = '.';
                    sb.append((char)('A'+c));
                    alive[c] = false;
                    flag = true;
                    break;
                
                }
            }
            
            // 이번 라운드에서 아무것도 못 지웠으면 종료.
            if(!flag) break;
        }
        
        for(int c = 0; c < 26; c++) {
            if(alive[c]) return "IMPOSSIBLE";
        }
        
        return sb.toString();
    }
    
    private boolean bfs(char[][] map, int aR, int aC, int bR, int bC) {
        int R = map.length, C = map[0].length;
        char target = map[bR][bC];
        
        // visited[r][c][dir] = 해당 방향으로 진입했을 때 최소 턴 수
        int[][][] visited = new int[R][C][4];
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                Arrays.fill(visited[r][c], 2);
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        
        for(int d = 0; d < dc.length; d++) {
            int nr = aR + dr[d];
            int nc = aC + dc[d];
            
            if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            
            if(pass(map, nr, nc, bR, bC)) {
                visited[nr][nc][d] = 0;
                q.add(new int[] {nr, nc, d, 0});
            }
        }
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], dir = cur[2], cnt = cur[3];
            
            if(r == bR && c == bC) return true; // 같은 문자 만난거임.
            
            for(int d = 0; d < dc.length; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                
                int turns = cnt + (d == dir ? 0 : 1);
                if(turns > 1) continue;
                if(!pass(map, nr, nc, bR, bC)) continue;
                
                if(visited[nr][nc][d] > turns) {
                    visited[nr][nc][d] = turns;
                    q.add(new int[] {nr, nc, d, turns});
                }
            }
        }
        return false;
    }
    
    // 통과 가능 : '.' or 도착 타일 좌표
    private boolean pass(char[][] map, int r, int c, int bR, int bC) {
        if(r == bR && c == bC) return true;
        return map[r][c] == '.';
    }
}