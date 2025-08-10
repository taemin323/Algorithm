/**
* 1일 1탐험 가능 던전을 최대한 많이 탐험하는 것이 목적.
* 현재 피로도 k
* dungeons[i][0] = 최소 필요 피로도, [i][1] = 소모 피로도
* 1 <= k <= 5,000
* 1 <= 던전의 개수 <= 8
* 1 <= 피로도 <= 1,000
*/
import java.util.*;

class Solution {
    int answer;
    public int solution(int k, int[][] dungeons) {
        
        dfs(0, k, dungeons);
        
        return answer;
    }
    
    public void dfs(int cnt, int k, int[][] dungeons) {
        for(int i = 0; i < dungeons.length; i++) {
            int tmp = dungeons[i][0];
            if(dungeons[i][0] <= k) {
                dungeons[i][0] = 9999;
                dfs(cnt+1, k - dungeons[i][1], dungeons);
                dungeons[i][0] = tmp;
            }
        }
        answer = Math.max(answer, cnt);
    }
    
}