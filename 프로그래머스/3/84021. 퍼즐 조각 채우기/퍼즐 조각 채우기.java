import java.util.*;

class Solution {
    
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    
    public int solution(int[][] game_board, int[][] table) {
        
        List<List<int[]>> emptySpace = new ArrayList<>();
        List<List<int[]>> blocks = new ArrayList<>();
        
        int N = game_board.length;
        int M = game_board[0].length;
        boolean[][] visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && game_board[i][j] == 0) {
                    emptySpace.add(bfs(i,j,N,M,visited,game_board,0));
                }
            }
        }
        
        N = table.length;
        M = table[0].length;
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && table[i][j] == 1) {
                    blocks.add(bfs(i,j,N,M,visited,table,1));
                }
            }
        }
        
        return match(emptySpace, blocks);
    }
    
    List<int[]> bfs(int r, int c, int N, int M, boolean[][] visited, int[][] board, int target) {
        List<int[]> result = new ArrayList<>();
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});
        visited[r][c] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            result.add(new int[] {curR, curC});
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                
                
                if(!visited[nr][nc] && board[nr][nc] == target) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
            }
        }
        
        return calculate(result);
    }
    
    // 정규화 메서드
    List<int[]> calculate(List<int[]> list) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for(int[] l : list) {
            minR = Math.min(minR, l[0]);
            minC = Math.min(minC, l[1]);
        }
        
        for(int[] l : list) {
            l[0] -= minR;
            l[1] -= minC;
        }
        
        return list;
    }
    
    int match(List<List<int[]>> emptySpace, List<List<int[]>> blocks) {
        int result = 0;
        boolean[] visited = new boolean[blocks.size()];
        
        for(int i = 0; i < emptySpace.size(); i++) {
            List<int[]> space = emptySpace.get(i);
            
            for(int j = 0; j < blocks.size(); j++) {
                if(visited[j]) continue;
                
                List<int[]> block = blocks.get(j);
                if(rotateAndCompare(space, block)) {
                    result += block.size();
                    visited[j] = true;
                    break;
                }
            }
        }
        
        return result;
    }
    
    boolean rotateAndCompare(List<int[]> space, List<int[]> block) {
        if(space.size() != block.size()) return false;
        
        // 회전시켰을 때 4개의 모양을 space와 비교
        List<int[]> rotate = block;
        for(int i = 0; i < 4; i++) {
            if(compare(space, rotate)) return true;
            
            if(i < 3) {
                rotate = rotated(rotate);
            }
        }
        
        return false;
    }
    
    boolean compare(List<int[]> space, List<int[]> block) {
        Collections.sort(space, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        Collections.sort(block, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        for(int i = 0; i < space.size(); i++) {
            int[] s = space.get(i);
            int[] b = block.get(i);
            
            if(s[0] != b[0] || s[1] != b[1]) return false;
        }
        
        return true;
    }
    
    List<int[]> rotated(List<int[]> block) {
        List<int[]> result = new ArrayList<>();
        
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        //시계방향으로 90도 회전
        for(int[] b : block) {
            int r = b[1];
            int c = -b[0];
            
            result.add(new int[] {r,c});
            
            if(r < minR) minR = r;
            if(c < minC) minC = c;
        }
        
        //정규화
        for(int[] i : result) {
            i[0] -= minR;
            i[1] -= minC;
        }
        
        return result;
    }
}