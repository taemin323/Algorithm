import java.util.*;

class Solution {
    StringBuilder sb = new StringBuilder();
    int answer = 0;
    int cnt = 0;
    boolean flag;
    
    public int solution(String word) {
        dfs(0, word);
        return answer;
    }
    
    void dfs(int len, String target) {
        if(len > 5 || flag) {
            return;
        }
        
        if(len > 0) {
            cnt++;
            
            // if(sb.toString().equals(target)) {
            //     answer = cnt;
            //     flag = true;
            //     return;
            // }
        }
        
        if(sb.toString().equals(target)) {
            answer = cnt;
            flag = true;
        }
        
        for(int i = 0; i < 5; i++) {
            sb.append("AEIOU".charAt(i));
            dfs(len+1, target);
            sb.deleteCharAt(sb.length()-1);
        }

    }
}