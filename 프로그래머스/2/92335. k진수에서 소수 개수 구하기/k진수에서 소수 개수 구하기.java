import java.util.*;
/**
* k진법으로 보았을 때가 아닌, 10진법으로 보았을 때 소수여야 함.
* 1 <= n <= 1,000,000
* 3 <= k <= 10
* 
* 1. k진수로 바꾸기
* 2. 규칙에 해당되는 숫자 뽑아내기
* 3. 그 숫자들이 10진법으로 보았을 때 소수이면 카운트 올리기.
* 예시처럼 11,11 이렇게 2개여도 따로 카운트해야함.
*/

class Solution {
    public int solution(int n, int k) {
        // k진수로 바꾸기
        String num = change(n, k);
        
        int answer = 0;
        long cur = 0;
        boolean inNum = false;
        
        for(int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if(c != '0') {
                cur = cur * 10 + (c - '0');
                inNum = true;
            } else {
                if(inNum && isPrime(cur)) answer++;
                cur = 0;
                inNum = false;
            }
        }
        
        if(inNum && isPrime(cur)) answer++;
        
        return answer;
    }
    
    // k진수로 변환
    private String change(int n, int k) {
        if(n == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            sb.append(n % k);
            n /= k;
        }
        return sb.reverse().toString();
    }
    
    private boolean isPrime(long x) {
        if(x < 2) return false;
        
        if(x % 2 == 0) return x == 2;
        
        long l = (long) Math.sqrt(x);
        
        for(long i = 3; i <= l; i += 2) {
            if(x % i == 0) return false;
        }
        return true;
    }
    
}