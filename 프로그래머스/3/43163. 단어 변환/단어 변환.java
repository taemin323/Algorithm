import java.util.*;
/**
* 가장 짧은 변환 과정 -> BFS
*/

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] visited = new boolean[words.length];
        
        if(!isWords(target, words)) return 0;
        
        Queue<String> q = new LinkedList<>();
        q.offer(begin);
        
        while(!q.isEmpty()) {
            int qSize = q.size();
            for(int k = 0; k < qSize; k++) {
                String cur = q.poll();
                
                if(cur.equals(target)) return answer;
                
                for(int i = 0; i < words.length; i++) {
                    if(!visited[i] && isDiff(cur, words[i])) {
                        visited[i] = true;
                        q.offer(words[i]);
                    }
                }
            }
            answer++;
        }
        
        return answer;
    }
    
    boolean isWords(String target, String[] words) {
        for(String word : words) {
            if(target.equals(word)) return true;
        }
        return false;
    }
    
    boolean isDiff(String word1, String word2) {
        int cnt = 0;
        
        for(int i = 0; i < word1.length(); i++) {
            if(word1.charAt(i) != word2.charAt(i)) cnt++;
        }
        
        return cnt == 1;
    }
}