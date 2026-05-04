import java.util.*;

class Solution {
    public int solution(int[] money) {
        
        // dp[i] : i번째 집까지 도달했을 때의 최댓값
        // 첫 번째 집을 포함하는 경우 -> 마지막 집 못 감.
        int[] dp1 = new int[money.length];
        dp1[0] = money[0];
        dp1[1] = money[0];
        int max1 = dp1[0];
    
        for(int i = 2; i < money.length-1; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
            max1 = Math.max(max1, dp1[i]);
        }
        
        // 첫 번째 집을 포함하지 않는 경우 -> 마지막 집 갈 확률 생김
        int[] dp2 = new int[money.length];
        dp2[1] = money[1];
        int max2 = dp2[1];
        
        for(int i = 2; i < money.length; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
            max2 = Math.max(max2, dp2[i]);
        }
        return Math.max(max1, max2);
    }
}