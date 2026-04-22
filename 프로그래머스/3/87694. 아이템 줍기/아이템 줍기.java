import java.util.*;

class Solution {
    int[][] map;
    boolean[][] visited;
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    int answer = 0;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        // 맵 확장 생성
        map = new int[101][101];
        visited = new boolean[101][101];
        
        // 경로 생성
        for(int i = 0; i < rectangle.length; i++) {
            int leftC = rectangle[i][0];
            int leftR = rectangle[i][1];
            int rightC = rectangle[i][2];
            int rightR = rectangle[i][3];
            
            makeRoute(leftC*2, leftR*2, rightC*2, rightR*2);
        }
        
        bfs(characterX*2, characterY*2, itemX*2, itemY*2);
        return answer/2;
    }
    
    void makeRoute(int leftC, int leftR, int rightC, int rightR) {
        // 테두리를 포함한 모든 영역 1로 채우기
        // 그 중 내부는 -1로 덮기
        
        for(int r = leftR; r <= rightR; r++) {
            for(int c = leftC; c <= rightC; c++) {
                
                // 사각형 내부는 -1 처리
                if(r > leftR && r < rightR && c > leftC && c < rightC) {
                    map[r][c] = -1;
                } else if(map[r][c] != -1) {// 다른 사각형 내부가 아니라면
                    // 테두리를 경로에 포함
                    map[r][c] = 1;
                }
            }
        }
        
        
    }
    
    void bfs(int startC, int startR, int targetC, int targetR) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startR, startC});
        visited[startR][startC] = true;
        
        while(!q.isEmpty()) {
            int qSize = q.size();
            for(int k = 0; k < qSize; k++) {
                int[] cur = q.poll();
                int curR = cur[0];
                int curC = cur[1];
                
                if(curR == targetR && curC == targetC) return;
                
                for(int d = 0; d < 4; d++) {
                    int nr = curR + dr[d];
                    int nc = curC + dc[d];
                    
                    if(nr < 0 || nr >= 101 || nc < 0 || nc >= 101) continue;
                    
                    if(!visited[nr][nc] && map[nr][nc] == 1) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
            answer++;
        }
    }
}