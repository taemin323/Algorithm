import java.util.*;
/*
* 방식은 똑같이 set에 만들 수 있는 모든 숫자 저장.
* 소수 판별 메서드 만들어서 set에 있는 숫자들 모두 검사
*/

class Solution {
    Set<Integer> set = new HashSet<>();
    boolean[] visited;
    
    public int solution(String numbers) {
        int answer = 0;
        
        visited = new boolean[numbers.length()];
        StringBuilder sb = new StringBuilder();
        
        perm(sb, numbers);
        
        for(int s : set) {
            if(isPrime(s)) answer++;
        }
        
        return answer;
    }
    
    boolean isPrime(int n) {
        if(n <= 1) return false;
        
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
    
    void perm(StringBuilder sb, String numbers) {
        
        if(sb.length() > 0) set.add(Integer.parseInt(sb.toString()));
        
        for(int i = 0; i < numbers.length(); i++) {
            if(visited[i]) continue;
            
            //아직 방문하지 않았다면
            visited[i] = true;
            perm(sb.append(numbers.charAt(i)), numbers);
            //백트래킹
            sb.deleteCharAt(sb.length()-1);
            visited[i] = false;
        }
    }
}