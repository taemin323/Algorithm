import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> result = new ArrayList<>();
        
        Map<String, Integer> map = new HashMap<>();
        int dictSize = 1;
        for(char c = 'A'; c <= 'Z'; c++) {
            map.put(String.valueOf(c), dictSize++);
        }
        
        int i = 0;
        while(i < msg.length()) {
            String w = "";
            int j = i;
            //현재 위치 i에서 시작하는 prefix를 한 글자씩 늘려가며 체크
            while(j < msg.length() && map.containsKey(w + msg.charAt(j))) {
                w += msg.charAt(j);
                j++;
            }
            
            // w는 사전에 있는 가장 긴 prefix
            result.add(map.get(w));
            if(j < msg.length()) {
                map.put(w + msg.charAt(j), map.size()+1);//새 항목 추가
            }
            
            i += w.length();
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}