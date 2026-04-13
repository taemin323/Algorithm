import java.util.*;

class Solution {
    Set<Integer> set = new HashSet<>();
    int answer = 0;
    public int solution(String numbers) {
        
        perm("", numbers);
        
        for(int num : set) {
            if(isPrime(num)) answer++;
        } 
        return answer;
    }
    
    void perm(String str, String numbers) {
        if(!str.equals("")) set.add(Integer.parseInt(str));
        
        for(int i = 0; i < numbers.length(); i++) {
            perm(str+numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i+1, numbers.length()));
        }
    }
    
    boolean isPrime(int n) {
        if(n == 0 || n == 1) return false;
        
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        
        return true;
    }
}