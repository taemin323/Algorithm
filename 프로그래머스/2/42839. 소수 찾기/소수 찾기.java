import java.util.*;
/**
* numbers로 만들 수 있는 모든 숫자를 set에 저장
* prev 문자열에 현재 문자를 더하고 numbers에서는 그 문자를 뺀걸 넘김
* 만들어진 set에서 소수인 것들만 카운트
*/

class Solution {
    Set<Integer> set = new HashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        
        perm("", numbers);
        
        for(int s : set) {
            if(isPrime(s)) answer++;//소수라면 answer에 추가
        }
        
        return answer;
    }
    
    void perm(String prev, String numbers) {
        if(!prev.equals("")) set.add(Integer.valueOf(prev));//만들어진 숫자 set에 저장
        
        for(int i = 0; i < numbers.length(); i++) {
            perm(prev+numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i+1, numbers.length()));
        }
    }
    
    boolean isPrime(int n) {
        if(n <= 1) return false;
        
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
}