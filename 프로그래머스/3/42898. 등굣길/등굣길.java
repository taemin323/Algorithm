import java.util.*;
/**
* dp[i][j] : 출발점에서부터 (i,j)까지 도달하는 최단경로의 수 저장
* 오른쪽, 아래쪽으로만 이동이 가능하니, dp[i][j] = dp[i-1][j] + dp[i][j-1]
*/

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int num = 1000000007;
        
        int[][] dp = new int[n+1][m+1];
        boolean[][] water = new boolean[n+1][m+1];
        for(int i = 0; i < puddles.length; i++) {
            int r = puddles[i][1];
            int c = puddles[i][0];
            
            water[r][c] = true;
        }
        
        
        dp[1][1] = 1;
        
        //맨위, 맨왼쪽은 무조건 최단경로의 수가 1개임
        for(int i = 2; i <= m; i++) {
            if(water[1][i]) break;
            
            dp[1][i] = 1;
        }
        for(int i = 2; i <= n; i++) {
            if(water[i][1]) break;
            
            dp[i][1] = 1;
        }
        
        for(int i = 2; i <= n; i++) {
            for(int j = 2; j <= m; j++) {
                if(!water[i][j]) dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % num;
            }
        }
        return dp[n][m];
    }
}