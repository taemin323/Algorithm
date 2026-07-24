import java.util.*;
/**
* 40분
* 빈칸이랑 퍼즐이 모양이 일치하는지 체크하려면 -> 좌표 정규화해서 비교
* 일단 각각 빈칸과 퍼즐을 따로 리스트에 저장한 뒤, 하나씩 대조해봐야됨.
* 1대1로 대조해볼 때 최대 반복 수는 4번 왜냐하면 퍼즐을 회전시켜가면서 대조해봐야되니까.
* 각 빈칸과 퍼즐은 저장할 때 bfs를 활용해서. 정규화 시켜버린 다음 저장하자.
*/

class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    int n;
    public int solution(int[][] game_board, int[][] table) {
        List<List<int[]>> spaces = new ArrayList<>();
        List<List<int[]>> puzzles = new ArrayList<>();
        
        n = game_board.length;
        
        boolean[][] visited = new boolean[n][n];
        
        //빈칸 저장
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && game_board[i][j] == 0) spaces.add(bfs(i, j, 0, game_board, visited));
            }
        }
        
        //퍼즐 저장
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && table[i][j] == 1) puzzles.add(bfs(i, j, 1, table, visited));
            }
        }
        
        return match(spaces, puzzles);   
    }
    
    //모양 찾는 bfs
    List<int[]> bfs(int i, int j, int target, int[][] board, boolean[][] visited) {
        List<int[]> result = new ArrayList<>();
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {i, j});
        visited[i][j] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            
            result.add(new int[] {r,c});
            
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                
                if(!visited[nr][nc] && board[nr][nc] == target) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
            }
        }
        
        
        return calculate(result);
    }
    
    //정규화 메서드
    List<int[]> calculate(List<int[]> list) {
        List<int[]> result = new ArrayList<>();
        
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for(int[] i : list) {
            minR = Math.min(minR, i[0]);
            minC = Math.min(minC, i[1]);
        }
        
        for(int[] i : list) {
            int r = i[0] - minR;
            int c = i[1] - minC;
            
            result.add(new int[]{r,c});
        }
        
        return result;
    }
    
    //spaces와 puzzles 비교해서 정답 도출 메서드
    int match(List<List<int[]>> spaces, List<List<int[]>> puzzles) {
        int answer = 0;
        
        boolean[] sVisited = new boolean[spaces.size()];
        boolean[] pVisited = new boolean[puzzles.size()];
        
        for(int i = 0; i < spaces.size(); i++) {
            List<int[]> space = spaces.get(i);
            for(int j = 0; j < puzzles.size(); j++) {
                List<int[]> puzzle = puzzles.get(j);
                if(!sVisited[i] && !pVisited[j] && compare(space, puzzle)) {
                    sVisited[i] = true;
                    pVisited[j] = true;
                    answer += puzzle.size();
                }
            }
        }
        return answer;
    }
    
    //space와 puzzle의 4가지 버전 비교
    boolean compare(List<int[]> space, List<int[]> puzzle) {
        if(space.size() != puzzle.size()) return false;
        
        List<int[]> rotate = puzzle;
        
        for(int i = 0; i < 4; i++) {
            if(check(space, rotate)) return true;
            
            if(i < 3) rotate = rotated(rotate);
        }
        
        return false;
    }
    
    //회전 메서드
    List<int[]> rotated(List<int[]> puzzle) {
        List<int[]> result = new ArrayList<>();
        
        for(int[] i : puzzle) {
            int r = i[1];
            int c = -i[0];
            
            result.add(new int[] {r,c});
        }
        
        return calculate(result);
    }
    
    //1대1 비교 메서드
    boolean check(List<int[]> space, List<int[]> puzzle) {
        
        Collections.sort(space, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        Collections.sort(puzzle, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        for(int i = 0; i < space.size(); i++) {
            if(space.get(i)[0] != puzzle.get(i)[0] || space.get(i)[1] != puzzle.get(i)[1]) return false;
        }
        
        return true;
    }
}