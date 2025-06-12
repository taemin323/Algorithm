import java.util.*;

class Solution {
    static Set<Integer> set;
    static boolean[] visited;
    
    public int solution(String numbers) {
        int answer = 0;
        set = new HashSet<>();
        visited = new boolean[numbers.length()];
        dfs(numbers, "", 0);
        
        for (Integer num: set) {
            if(isPrime(num)) answer++;
        }
    
        return answer;
    }
    
    public static void dfs(String numbers, String curr, int depth) {
        //numbers의 끝까지 다 탐색했다면 종료
        if (depth > numbers.length()) {
            return;
        }
        
        for (int i = 0; i < numbers.length(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                set.add(Integer.parseInt(curr + numbers.charAt(i)));
                dfs(numbers, curr + numbers.charAt(i), depth + 1);
                visited[i] = false;
            }
        }
    }
    
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}