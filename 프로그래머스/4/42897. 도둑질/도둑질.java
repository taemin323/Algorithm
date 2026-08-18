import java.util.*;
/**
* 25분
* 첫 번째 집을 방문 했냐/안했냐로 나눠서 진행
* dp[i] = i번째 집까지 도달했을 때 훔친 돈의 최댓값
*/

class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        //첫 번째 집 방문, 마지막 집은 확정적으로 못감.
        int[] dp1= new int[n];
        
        dp1[0] = money[0];
        dp1[1] = money[0];
        
        for(int i = 2; i < n-1; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
        }
        
        //첫 번째 집 방문 안하면, 마지막 집은 가거나 못가거나 둘 다 가능.
        int[] dp2 = new int[n];
        dp2[1] = money[1];
        for(int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }
        
        return Math.max(dp1[n-2], dp2[n-1]);        
    }
}