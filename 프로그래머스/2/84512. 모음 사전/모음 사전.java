import java.util.*;

class Solution {
    private int answer = 0;
    private int cnt = 0;
    private String[] base = {"A", "E", "I", "O", "U"};
    private StringBuilder sb = new StringBuilder();
    private String target;
    
    public int solution(String word) {
        target = word;
        
        dfs(0);
        
        return answer;
    }
    
    private void dfs(int depth) {
        if(depth > 5) return;
        
        if(depth > 0) {
            cnt++;
            if(sb.toString().equals(target)) {
                answer = cnt;
                return;
            }
        }
        
        for(int i = 0; i < base.length; i++) {
            sb.append(base[i]);
            dfs(depth + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}