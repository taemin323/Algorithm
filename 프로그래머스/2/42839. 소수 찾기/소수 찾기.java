import java.util.*;

class Solution {
    Set<Integer> set;
    
    public int solution(String numbers) {
        int answer = 0;
        
        set = new HashSet<>();
        
        perm("", numbers);
        
        for(int num : set) {
            if(isPrime(num)) answer++;
        }
        return answer;
    }
    
    public void perm(String prev, String numbers) {
        if(!prev.equals("")) set.add(Integer.valueOf(prev));
        
        for(int i = 0; i < numbers.length(); i++) {
            perm(prev + numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i+1, numbers.length()));
        }
    }
    
    public boolean isPrime(int n) {
        if(n < 2) return false;
        
        for(int i = 2; i <= n; i++) {
            if(n == i) continue;
            
            if(n % i == 0) return false;
        }
        
        return true;
    }
}