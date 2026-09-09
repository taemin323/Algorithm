import java.util.*;
/**
* 25분 -> x
* 50분 -> x
* 범위가 크기 때문에 이중 반복문은 안됨.
* - : 해당 조건은 고려하지 않겠다는 뜻.
*/

class Solution {
    public int[] solution(String[] info, String[] query) {

        Map<String, List<Integer>> map = new HashMap<>();
        
        //지원자 정보 전처리
        for(int i = 0; i < info.length; i++) {
            String[] parts = info[i].split(" ");
                
            String[] conditions = {parts[0], parts[1], parts[2], parts[3]};
            int score = Integer.parseInt(parts[4]);
            
            //0000 ~ 1111
            //총 16가지
            for(int mask = 0; mask < 16; mask++) {
                StringBuilder key = new StringBuilder();
                
                for(int j = 0; j < 4; j++) {
                    //j번째 비트가 1이면 "-"
                    if((mask & (1 << j))!= 0) {
                        key.append("-");
                    } else {
                        key.append(conditions[j]);
                    }
                }
                map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(score);
            }
            
        }
        
        //점수 정렬
        for(List<Integer> scores : map.values()) {
            Collections.sort(scores);
        }
        
        int[] answer = new int[query.length];
        
        // query 처리
        for(int i = 0; i < query.length; i++) {
            String[] parts = query[i].replace(" and ", " ").split(" ");
            
            String key = parts[0] + parts[1] + parts[2] + parts[3];
            int score = Integer.parseInt(parts[4]);
            
            List<Integer> scores = map.get(key);
            
            if(scores == null) {
                answer[i] = 0;
                continue;
            }
            
            int cnt = lowerBound(scores, score);
            
            answer[i] = scores.size() - cnt;
        }
        
        return answer;
    }
    
    int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();
        
        while(left < right) {
            int mid = (right - left)/2 + left;
            
            if(list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
}