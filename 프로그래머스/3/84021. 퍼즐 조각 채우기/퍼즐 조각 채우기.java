import java.util.*;
/**
* 일단 빈칸들과, 블록들 bfs로 각가 리스트에 저장하기
* 저장할 때 정규화해서 저장해야 나중에 비교할 수 있음
* 빈칸과 블록를 비교할 때는 총 4번을 비교해야함. 회전시켜서 비교해야 되니까
*/

class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    boolean[][] visited;
    int n;
    
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        
        List<List<int[]>> spaces = new ArrayList<>();
        List<List<int[]>> blocks = new ArrayList<>();
        
        visited = new boolean[n][n];
        
        //빈칸들 저장
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && game_board[i][j] == 0) {
                    visited[i][j] = true;
                    spaces.add(bfs(i, j, game_board, 0));
                }
            }
        }
        
        visited = new boolean[n][n];
        //블록들 저장
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && table[i][j] == 1) {
                    visited[i][j] = true;
                    blocks.add(bfs(i, j, table, 1));
                }
            }
        }
        
        
        return match(spaces, blocks);
    }
    
    //빈칸과 블록을 매칭시켜 정답 도출하는 메서드
    int match(List<List<int[]>> spaces, List<List<int[]>> blocks) {
        int cnt = 0;
        
        boolean[] sUsed = new boolean[spaces.size()];
        boolean[] bUsed = new boolean[blocks.size()];
        
        for(int j = 0; j < spaces.size(); j++) {
            List<int[]> space = spaces.get(j);
            
            for(int i = 0; i < blocks.size(); i++) {
                List<int[]> block = blocks.get(i);
                
                if(!sUsed[j] && !bUsed[i] && rotateAndCompare(space, block)) {
                    bUsed[i] = true;
                    sUsed[j] = true;
                    cnt += block.size();
                    break;
                }
            }
        }
        
        return cnt;
    }
    
    //빈칸과 블록 비교 : 회전한 버전 4개 모두와 비교
    boolean rotateAndCompare(List<int[]> space, List<int[]> block) {
        //칸 개수가 다르면 맞는 블록이 아님
        if(space.size() != block.size()) return false;
        
        //블록을 회전시켜 보면서 빈칸에 넣어보기
        List<int[]> rotate = block;
        for(int i = 0; i < 4; i++) {
            if(compare(space, rotate)) return true;
            
            if(i < 3) {
                rotate = rotated(rotate);              
            }
        }
        
        return false;
    }
    
    //진짜 1:1 비교
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
    
    //회전 메서드
    List<int[]> rotated(List<int[]> block) {
        List<int[]> result = new ArrayList<>();
        
        for(int[] b : block) {
            int r = b[1];
            int c = -b[0];
            
            result.add(new int[] {r, c});
        }
        
        //여기서도 정규화
        return calculate(result);
    }
    
    // 빈칸/블록 저장 메서드
    List<int[]> bfs(int i, int j, int[][] board, int target) {
        List<int[]> result = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {i, j});
        result.add(new int[] {i, j});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                
                if(!visited[nr][nc] && board[nr][nc] == target) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                    result.add(new int[] {nr, nc});
                }
            }
        }
        
        return calculate(result);
    }
    
    //정규화 메서드
    List<int[]> calculate(List<int[]> list) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for(int[] i : list) {
            minR = Math.min(minR, i[0]);
            minC = Math.min(minC, i[1]);
        }
        
        for(int[] i : list) {
            i[0] -= minR;
            i[1] -= minC;
        }
        
        return list;
    }
}