import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
        // dp[i] : N을 i개 사용해서 만들 수 있는 수들의 집합
        List<Set<Integer>> dp = new ArrayList<>();
        for(int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }
        
        String str = "";
        for(int i = 1; i <= 8; i++) {
            str += N;
            dp.get(i).add(Integer.parseInt(str));
        }
        
        for(int i = 1; i <= 8; i++) {
            for(int j = 1; j < i; j++) {
                for(int a : dp.get(j)) {
                    for(int b : dp.get(i-j)) {
                        dp.get(i).add(a+b);
                        dp.get(i).add(a-b);
                        dp.get(i).add(a*b);
                        if(b != 0) dp.get(i).add(a/b);
                    }
                }
            }
            if(dp.get(i).contains(number)) return i;
        }
        
        return -1;
    }
}