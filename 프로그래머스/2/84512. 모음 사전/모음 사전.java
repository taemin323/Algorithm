import java.util.*;

class Solution {
    List<String> list = new ArrayList<>();
    
    public int solution(String word) {
        
        dfs("", 0);
        
        return list.indexOf(word);
    }
    
    void dfs(String str, int len) {
        if(len > 5) return;
        
        list.add(str);
        
        for(int i = 0; i < 5; i++) {
            String next = str + "AEIOU".charAt(i);
            dfs(next, len+1);
        }
    }
}