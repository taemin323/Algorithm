import java.util.*;

class Solution {
    int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        dfs(0,k,dungeons);
        return answer;
    }
    
    void dfs(int cnt, int k, int[][] dungeons) {
        answer = Math.max(answer, cnt);
        
        for(int i = 0; i < dungeons.length; i++) {
            if(k >= dungeons[i][0]) {
                int cur = dungeons[i][0];
                dungeons[i][0] = 50001;
                
                dfs(cnt+1, k-dungeons[i][1], dungeons);
                
                dungeons[i][0] = cur;
            }
        }
    }
}