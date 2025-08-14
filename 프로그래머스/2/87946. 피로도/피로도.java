/**
* 유저의 현재 피로도 k 1 <= k <= 5000
* 던전의 개수 1 이상 8 이하
* dungeons[i][0] = 최소 필요 피로도, dungeons[i][1] = 소모 피로도
*/
import java.util.*;

class Solution {
    boolean[] visited;
    int answer = 0;
    public int solution(int k, int[][] dungeons) {
        
        visited = new boolean[dungeons.length];
        
        dfs(0,k,dungeons);
        
        return answer;
    }
    
    public void dfs(int cnt, int k, int[][] dungeons) {
        
        for(int i = 0; i < dungeons.length; i++) {
            if(!visited[i] && dungeons[i][0] <= k) {
                visited[i] = true;
                dfs(cnt+1, k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
        
        answer = Math.max(answer, cnt);
    }
}