import java.util.*;

class Solution {
    public int solution(String numbers) {
        int answer = 0;
        
        HashSet<Integer> set = new HashSet<>();
        
        perm("", numbers, set);        
        
        for(int num : set) {
            if(isPrime(num)) answer++;
        }
        
        return answer;
    }
    
    public void perm(String prev, String numbers, HashSet<Integer> set) {
        if(!prev.equals("")) set.add(Integer.parseInt(prev));
        
        for(int i = 0; i < numbers.length(); i++) {
            perm(prev + numbers.charAt(i), numbers.substring(0,i) + numbers.substring(i+1, numbers.length()), set);
        }
    }
    
    public boolean isPrime(int n) {
        if(n == 0 || n == 1) return false;
        
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        
        return true;
    }
}