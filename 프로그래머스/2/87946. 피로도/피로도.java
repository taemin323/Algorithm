import java.util.*;

class Solution {
    int answer = 0;
    boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(0, dungeons, k);
        return answer;
    }
    
    public void dfs(int cnt, int[][] dungeons, int k) {
        answer = Math.max(answer, cnt);
        
        for(int i = 0; i < dungeons.length; i++) {
            if(!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                
                dfs(cnt+1, dungeons, (k - dungeons[i][1]));
                
                visited[i] = false;
            }
        }
    }
}