import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;
        
        List<String> cache = new ArrayList<>();
        int answer = 0;
        
        for(int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();
            cities[i] = city;
            
            if(!cache.contains(city)) {
                if(cache.size() >= cacheSize) {
                    cache.remove(0);
                    cache.add(city);
                    answer += 5;
                } else {
                    cache.add(city);
                    answer += 5;
                }
            } else {
                cache.remove(city);
                cache.add(city);
                answer += 1;
            }
        }
        
        return answer;
    }
}