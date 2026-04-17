import java.util.*;

class Solution {
    class Word {
        String word;
        int cnt;
        
        public Word(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
    
    boolean[] visited;
    int answer = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        
        if(!check(target, words)) return 0;
        
        bfs(begin, target, words, 0);
        
        return answer;
    }
    
    void bfs(String begin, String target, String[] words, int cnt) {
        Queue<Word> q = new LinkedList<>();
        q.offer(new Word(begin, 0));
        
        while(!q.isEmpty()) {
            Word cur = q.poll();
            
            if(cur.word.equals(target)) {
                answer = cur.cnt;
                return;
            }
            
            for(int i = 0; i < words.length; i++) {
                if(!visited[i] && isDiff(cur.word, words[i])) {
                    visited[i] = true;
                    q.offer(new Word(words[i], cur.cnt+1));
                }
            }
        }
    }
    
    boolean check(String target, String[] words) {
        for(String word : words) {
            if(word.equals(target)) return true;
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