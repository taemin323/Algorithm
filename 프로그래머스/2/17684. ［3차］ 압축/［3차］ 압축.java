import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();
        
        int dictSize = 1;
        for(char c = 'A'; c <= 'Z'; c++) {
            dict.put(String.valueOf(c), dictSize++);
        }
        
        int i = 0;
        while(i < msg.length()) {
            String w = "";
            
            int j = i;
            while(j < msg.length() && dict.containsKey(w + msg.charAt(j))) {
                
                w += msg.charAt(j);
                j++;
            }
            
            result.add(dict.get(w));
            
            if(j < msg.length()) {
                dict.put(w+msg.charAt(j), dict.size()+1);
            }
            
            i += w.length();
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}