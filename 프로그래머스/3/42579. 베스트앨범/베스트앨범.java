import java.util.*;

class Solution {
    class Music {
        String genre;
        int idx;
        int plays;
        
        Music(String genre, int idx, int plays) {
            this.genre = genre;
            this.idx = idx;
            this.plays = plays;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, Integer> genreSum = new HashMap<>();
        List<Music> musicList = new ArrayList<>();
        
        for(int i = 0; i < genres.length; i++) {
            genreSum.put(genres[i], genreSum.getOrDefault(genres[i], 0) + plays[i]);
            
            musicList.add(new Music(genres[i], i, plays[i]));
        }
        
        // 정렬하기
        musicList.sort((a,b) -> {
            
            // 장르별 재생수로 내림차순
            if(!a.genre.equals(b.genre)) {
                return genreSum.get(b.genre) - genreSum.get(a.genre);
            }
            
            // 같은 장르에서 재생수 내림차순
            if(b.plays != a.plays) {
                return b.plays - a.plays;
            }
            
            // 재생수 같으면 인덱스 오름차순
            return a.idx - b.idx;
        });
        
        // 장르별 2개만 추출
        Map<String, Integer> cnt = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        
        for(Music music : musicList) {
            cnt.put(music.genre, cnt.getOrDefault(music.genre, 0) + 1);
            
            if(cnt.get(music.genre) <= 2) {
                result.add(music.idx);
            }
        }
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}