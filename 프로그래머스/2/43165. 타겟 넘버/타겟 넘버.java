import java.util.*;

class Solution {
    int answer = 0;
    String[] ops;
    
    public int solution(int[] numbers, int target) {
        ops = new String[numbers.length];
        dfs(0, 0, numbers, target);
        return answer;
    }
    
    void dfs(int depth, int idx, int[] numbers, int target) {
        if(depth == ops.length) {
            int sum = 0;
            for(int i = 0; i < ops.length; i++) {
                if(ops[i].equals("+")) sum += numbers[i];
                else sum -= numbers[i];
            }
            
            if(sum == target) answer++;
            return;
        }
        
        ops[idx] = "+";
        dfs(depth+1, idx+1, numbers, target);
        ops[idx] = "-";
        dfs(depth+1, idx+1, numbers, target);
    }
}