import java.util.*;

class Solution {
    int answer = 0;
    int cnt = 0;
    
    public int solution(String word) {
        
        dfs("", word, 0);       
        return answer;
    }
    
    void dfs(String str, String target, int depth) {
        if(str.equals(target)) {
            answer = cnt;
            return;
        }
        
        if(depth == 5) return;
        
        for(int i = 0; i < 5; i++) {
            cnt++;
            dfs(str + "AEIOU".charAt(i), target, depth+1);
        }
    }
}