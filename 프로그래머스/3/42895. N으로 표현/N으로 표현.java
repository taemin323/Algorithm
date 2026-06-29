import java.util.*;
/**
* 최솟값이 8보다 크면 어차피 -1임
* 1부터 8개까지 numbers를 만들 수 있는지 체크?
* 1개일 땐 어차피 사칙연상이 안되니 바로 비교
* 2개부터는 n+n, n/n, n*n, n-n이 됨.
* 3개부터는 사칙연산과 괄호를 생각해야됨.
* 3개짜리 = 1개짜리 (연산) 2개짜리 또는 2개짜리 (연산) 1개짜리
* 즉 k개 = 1개 op (k-1)개
*       = 2개 op (k-2)개
*/

class Solution {
    public int solution(int N, int number) {
        
        //dp[i] = N을 i개 써서 만들 수 있는 수의 집합
        Set<Integer>[] dp = new HashSet[9];
        
        String str = "";
        for(int i = 1; i <= 8; i++) {
            str += N;
            dp[i] = new HashSet<>();
            dp[i].add(Integer.parseInt(str));
            
            for(int k = 1; k < i; k++) {
                for(int a : dp[k]) {
                    for(int b : dp[i-k]) {
                        dp[i].add(a - b);
                        dp[i].add(a + b);
                        dp[i].add(a * b);
                        if(b != 0) dp[i].add(a / b);    
                    }
                }
            }
            
            if(dp[i].contains(number)) return i;
        }
        return -1;
    }
}