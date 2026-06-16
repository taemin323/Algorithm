import java.util.*;

class Solution {
    Set<Integer> set = new HashSet<>();
    public int solution(String numbers) {
        int answer = 0;
        
        perm("", numbers);
        
        for(int s : set) {
            if(check(s)) answer++;
        }
        
        return answer;
    }
    
    boolean check(int num) {
        if(num <= 1) return false;
        
        for(int i = 2 ; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        
        return true;
    }
    
    void perm(String prev, String numbers) {
        if(!prev.equals("")) set.add(Integer.valueOf(prev));
        
        for(int i = 0; i < numbers.length(); i++) {
            perm(prev + numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i+1, numbers.length()));
        }
    }
}