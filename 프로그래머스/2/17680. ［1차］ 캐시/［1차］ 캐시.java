import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;
        
        List<String> cach = new ArrayList<>();
        int answer = 0;
        
        
        for(int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();
            
            if(cach.size() >= cacheSize) {
                if(cach.contains(city)) {
                    cach.remove(city);
                    cach.add(city);
                    answer += 1;
                } else {
                    cach.remove(0);
                    cach.add(city);
                    answer += 5;
                }
            } else {
                if(cach.contains(city)) {
                    cach.remove(city);
                    cach.add(city);
                    answer += 1;
                } else {
                    cach.add(city);
                    answer += 5;;
                }
                
            }
            
        }
        
        return answer;
    }
}