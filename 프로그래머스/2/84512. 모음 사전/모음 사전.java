import java.util.*;

class Solution {
    int answer = 0;
    List<String> list = new ArrayList<>();
    public int solution(String word) {
        dfs("", 0);
        
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).equals(word)) answer = i;
        }
        
        return answer;
    }
    
    void dfs(String str, int depth) { 
        
        list.add(str);
        
        if(depth == 5) return;
        
        for(int i = 0; i < 5; i++) {
            dfs(str + "AEIOU".charAt(i), depth+1);
        }
    }
}