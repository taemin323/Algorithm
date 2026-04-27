import java.util.*;

class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    List<List<int[]>> spaces = new ArrayList<>();
    List<List<int[]>> blocks = new ArrayList<>();
    
    public int solution(int[][] game_board, int[][] table) {
        int n = game_board.length;
        int m = game_board[0].length;
        boolean[][] visited = new boolean[n][m];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visited[i][j] && game_board[i][j] == 0) {
                    spaces.add(bfs(i,j,n,m,game_board,visited,0));
                }
            }
        } 
        
        n = table.length;
        m = table[0].length;
        visited = new boolean[n][m];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visited[i][j] && table[i][j] == 1) {
                    blocks.add(bfs(i,j,n,m,table,visited,1));
                }
            }
        }
        return match(spaces, blocks);
    }
    
    List<int[]> bfs(int i, int j, int n, int m,int[][] board, boolean[][] visited, int target) {
        List<int[]> result = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i, j});
        visited[i][j] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            result.add(new int[] {curR, curC});
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                
                if(!visited[nr][nc] && board[nr][nc] == target) {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        return calculate(result);
    }
    
    List<int[]> calculate(List<int[]> result) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for(int[] i : result) {
            minR = Math.min(minR, i[0]);
            minC = Math.min(minC, i[1]);
        }
        
        for(int[] i : result) {
            i[0] -= minR;
            i[1] -= minC;
        }
        
        return result;
    }
    
    int match(List<List<int[]>> spaces, List<List<int[]>> blocks) {
        int result = 0;
        boolean[] used = new boolean[blocks.size()];
        
        for(int i = 0; i < spaces.size(); i++) {
            List<int[]> space = spaces.get(i);
            for(int j = 0; j < blocks.size(); j++) {
                if(used[j]) continue;
                
                List<int[]> block = blocks.get(j);
                
                if(rotatedAndCompare(space, block)) {
                    used[j] = true;
                    result += block.size();
                    break;
                }
            }
        }
        return result;
    }
    
    boolean rotatedAndCompare(List<int[]> space, List<int[]> block) {
        if(space.size() != block.size()) return false;
        
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
        
        for(int[] i : block) {
            int r = i[1];
            int c = -i[0];
            
            result.add(new int[] {r,c});
            
            minR = Math.min(minR, r);
            minC = Math.min(minC, c);
        }
        
        //정규화
        for(int[] i : result) {
            i[0] -= minR;
            i[1] -= minC;
        }
        return result;
    }
}