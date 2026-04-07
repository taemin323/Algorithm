import java.util.*;

class Solution {
    int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        dfs(0, dungeons, k);
        return answer;
    }
    
    public void dfs(int cnt, int[][] dungeons, int k) {
        answer = Math.max(answer, cnt);
        
        for(int i = 0; i < dungeons.length; i++) {
            int a = dungeons[i][0];
            
            if(k >= dungeons[i][0]) {
                dungeons[i][0] = 5001;
                
                dfs(cnt+1, dungeons, (k - dungeons[i][1]));
                
                dungeons[i][0] = a;
            }
        }
    }
}