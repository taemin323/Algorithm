import java.util.*;

class Solution {
    private static int[] dr = {-1,1,0,0};
    private static int[] dc = {0,0,-1,1};
    
    public int solution(int[][] game_board, int[][] table) {
        List<List<int[]>> emptySpace = new ArrayList<>();
        List<List<int[]>> blocks = new ArrayList<>();
        
        int N = game_board.length;
        int M = game_board[0].length;
        boolean[][] visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(game_board[i][j] == 0 && !visited[i][j]) {
                    emptySpace.add(bfs(i,j,N,M,game_board,visited,0));
                }
            }
        }
        
        N = table.length;
        M = table[0].length;
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(table[i][j] == 1 && !visited[i][j]) {
                    blocks.add(bfs(i,j,N,M,table,visited,1));
                }
            }
        }
        
        return match(emptySpace, blocks);
    }
    
    private static List<int[]> bfs(int i, int j, int N, int M, int[][] board, boolean[][] visited, int target) {
        List<int[]> result = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {i,j});
        visited[i][j] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            result.add(cur);
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                
                if(visited[nr][nc]) continue;
                
                if(board[nr][nc] != target) continue;
                
                visited[nr][nc] = true;
                q.add(new int[] {nr, nc});
            }
        }
        
        return calculate(result);
    }
    
    private static List<int[]> calculate(List<int[]> data) {
        if(data == null || data.isEmpty()) return data;
        
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for(int[] d : data) {
            minR = Math.min(minR, d[0]);
            minC = Math.min(minC, d[1]);
        }
        
        for(int[] d : data) {
            d[0] -= minR;
            d[1] -= minC;
        }
        return data;
    }
    
    private static int match(List<List<int[]>> emptySpace, List<List<int[]>> blocks) {
        int result = 0;
        boolean[] used = new boolean[blocks.size()];
        
        for(int i = 0; i < emptySpace.size(); i++) {
            List<int[]> space = emptySpace.get(i);
            
            for(int j = 0; j < blocks.size(); j++) {
                if(used[j]) continue;
                
                List<int[]> block = blocks.get(j);
                if(rotateAndCompare(space, block)) {
                    result += block.size();
                    used[j] = true;
                    break;
                }
            }
        }
        return result;
    }
    
    private static boolean rotateAndCompare(List<int[]> space, List<int[]> block) {
        if(space.size() != block.size()) return false;
        
        List<int[]> rotated = block;
        for(int i = 0; i < 4; i++) {// 회전
            if(compare(space, rotated)) return true;
            
            if(i < 3) {
                rotated = rotate(rotated);
            }
        }
        return false;
    }
    
    private static List<int[]> rotate(List<int[]> block) {
        List<int[]> result = new ArrayList<>();
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        for(int[] b : block) {
            int r = b[1];
            int c = -b[0];
            
            result.add(new int[] {r, c});
            
            if(r < minR) minR = r;
            if(c < minC) minC = c;
        }
        
        for(int[] r : result) {
            r[0] -= minR;
            r[1] -= minC;
        }
        return result;
    }
    
    private static boolean compare(List<int[]> space, List<int[]> block) {
        Collections.sort(space, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]); // r이 같다면 c 오름차순
        Collections.sort(block, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        for(int i = 0; i < space.size(); i++) {
            int[] s = space.get(i);
            int[] b = block.get(i);
            if(s[0] != b[0] || s[1] != b[1]) {
                return false;
            }
        }
        
        return true;
    }
}