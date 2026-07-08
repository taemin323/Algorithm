import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        int n = cities.length;
        List<String> cache = new ArrayList<>();
        
        if(cacheSize == 0) return cities.length * 5;
        
        for(int i = 0; i < n; i++) {
            cities[i] = cities[i].toLowerCase();
            
            String city = cities[i];
            
            // 캐시 안에 현재 도시가 있는 경우
            if(cache.contains(city)) {
                cache.remove(city);
                cache.add(city);
                answer += 1;
            } else {// 캐시 안에 현재 도시가 없는 경우
                //캐시가 꽉 차 있다면
                if(cache.size() >= cacheSize) {
                    cache.remove(0);
                }
                cache.add(city);
                answer += 5;
            }
        }
        
        return answer;
    }
}