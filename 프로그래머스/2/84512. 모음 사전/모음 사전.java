import java.util.*;

class Solution {
    private String[] base = {"A", "E", "I", "O", "U"};
    private String target;
    private int cnt = 0;
    private int answer = 0;
    private boolean found = false;
    private StringBuilder sb = new StringBuilder();
    
    public int solution(String word) {
        target = word;
        List<String> list = new ArrayList<>();
        
        dfs(0);
        
        return answer;
    }
    
    private void dfs(int depth) {
        if(found) return;
        
        if(depth > 5) return;
        
        if(depth > 0) {
            cnt++;
            if(sb.toString().equals(target)) {
                answer = cnt;
                found = true;
                return;
            }
        }
        
        // 모음 5개를 순서대로 붙이기
        for(int i = 0; i < 5; i++) {
            sb.append(base[i]);
            dfs(depth + 1);
            sb.deleteCharAt(sb.length() - 1); // 백트래킹
        }
    }
}