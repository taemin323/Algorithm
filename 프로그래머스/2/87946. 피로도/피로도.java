import java.util.*;

class Solution {
    boolean[] visited;
    int answer = Integer.MIN_VALUE;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(0, k, dungeons);
        
        return answer;
    }
    
    void dfs(int depth, int k, int[][] dungeons) {
        if(check(dungeons, k)) {
            int cnt = 0;
            for(boolean b : visited) {
                if(b) cnt++;
            }
            
            answer = Math.max(answer, cnt);
        }
        
        for(int i = 0; i < dungeons.length; i++) {
            if(k >= dungeons[i][0] && !visited[i]) {
                visited[i] = true;
                dfs(depth+1, k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
    }
    
    boolean check(int[][] dungeons, int k) {
        for(int i = 0; i < visited.length; i++) {
            if(!visited[i] && k >= dungeons[i][0]) return false;
        }
        return true;
    }
}