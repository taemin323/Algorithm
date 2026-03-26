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
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        if(!check(target, words)) return 0;
        
        boolean[] visited = new boolean[words.length];
        Queue<Word> q = new LinkedList<>();
        q.offer(new Word(begin, 0));
        
        while(!q.isEmpty()) {
            Word cur = q.poll();
            
            if(cur.word.equals(target)) return cur.cnt;
            
            for(int i = 0; i < words.length; i++) {
                if(!visited[i] && isDiff(cur.word, words[i])) {
                    visited[i] = true;
                    q.offer(new Word(words[i], cur.cnt + 1));
                }
            }
        }
        
        return 0;
    }
    
    private boolean check(String target, String[] words) {
        for(String word : words) {
            if(target.equals(word)) return true;
        }
        
        return false;
    }
    
    private boolean isDiff(String word1, String word2) {
        int cnt = 0;
        for(int i = 0; i < word1.length(); i++) {
            if(word1.charAt(i) != word2.charAt(i)) cnt++;
        }
        
        return cnt == 1;
    }
}