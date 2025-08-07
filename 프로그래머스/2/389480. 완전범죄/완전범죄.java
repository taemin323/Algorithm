/**
* info[i][0] = A에 대한 흔적
* info[i][1] = B에 대한 흔적
* 각 물건에 대해 두 도둑이 남기는 흔적의 개수는 1이상 3이하
* 흔적의 누적 개수가 n개 이상 -> A 잡힘
* 흔적의 누적 개수가 M개 이상 -> B 잡힘
*/

import java.util.*;

class Solution {
    boolean[][][] dp;
    int answer;
    public int solution(int[][] info, int n, int m) {
        answer = n + 1;
        dp = new boolean[info.length][n+1][m+1];
        dfs(info, 0, 0, 0, n, m);
        
        return answer > n ? -1 : answer;
    }
    
    public void dfs(int[][] info, int idx, int a, int b, int n, int m) {
        if(a >= n || b >= m) return;
        
        if(idx >= info.length) {
            answer = Math.min(answer, a);
            return;
        }
        
        if(dp[idx][a][b]) return;
        dp[idx][a][b] = true;
        
        dfs(info, idx+1, a+info[idx][0], b, n, m);
        dfs(info, idx+1, a, b+info[idx][1], n, m);
    }
}