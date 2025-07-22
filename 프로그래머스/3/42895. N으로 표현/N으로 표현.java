import java.util.*;
/**
* 1 <= N <= 9
* 1 <= number <= 32,000
*/

class Solution {
    public int solution(int N, int number) {
        
        List<Set<Integer>> dp = new ArrayList<>();
        for(int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }
        
        // N 연속인 숫자(N, NN, NNN...)
        int tmp = 0;
        for(int i = 1; i <=8; i++) {
            tmp = tmp * 10 + N;
            dp.get(i).add(tmp);
        }
        
        // 조합으로 나머지 연산 구하기
        for(int i = 1; i <= 8; i++) {
            //j개의 N과 (i-j)개의 N을 조합
            for(int j = 1; j < i; j++) {
                for(int a: dp.get(j)) {
                    for(int b: dp.get(i - j)) {
                        dp.get(i).add(a + b);
                        dp.get(i).add(a - b);
                        dp.get(i).add(a * b);
                        if (b != 0) {
                            dp.get(i).add(a / b);
                        }
                    }
                }
            }
            if(dp.get(i).contains(number)) {
                return i;
            }
        }
        
     
        
        return -1;
    }
}