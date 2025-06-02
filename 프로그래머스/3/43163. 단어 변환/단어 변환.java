import java.util.*;

class Solution {
    static class Word {
        String word;
        int step;
        
        Word(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }
    
    // target이 words 안에 있는지 확인
    boolean check(String target, String[] words) {
        for (String word: words){
            if(word.equals(target)) {
                return true;
            }
        }
        return false;
    }
    
    // 두 단어가 한 글자만 다른지 확인
    boolean isOneLetterDiff(String cur, String next) {
        int diff = 0;
        for(int i = 0; i < cur.length(); i++) {
            if(cur.charAt(i) != next.charAt(i)) {
                diff++;
            }
            if (diff > 1) return false;
        }
        return diff == 1;
    }
    
    public int solution(String begin, String target, String[] words) {
        int cnt = 0;
        
        if(!check(target, words)) return 0;
        
        boolean[] visited = new boolean[words.length];
        Queue<Word> q = new LinkedList<Word>();
        q.add(new Word(begin, 0));

        while(!q.isEmpty()) {
            Word cur = q.poll();
            
            if(cur.word.equals(target)) {
                return cur.step;
            }
            
            for (int i = 0; i < words.length; i++) {
                if(!visited[i] && isOneLetterDiff(cur.word, words[i])){
                    visited[i] = true;
                    q.add(new Word(words[i], cur.step+1));
                }
            }
        }
        
        return 0;
    }
}