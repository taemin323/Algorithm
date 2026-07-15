import java.util.*;
/**
* 사전 -> Map으로 관리.
* i = 0 ~ msg.length-1까지 while
* while문 내부에서 k, ka, kao를 순서대로 사전에 있는지 체크
* 없는 문자열이 나오면 그걸 사전에 저장하고 그 이전 즉 있는 문자열을 그대로 출력 list에 담고
* i는 그 문자열만큼 건너뛴 위치로 이동해서 다시 while문 내부 반복
*/

class Solution {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();
        
        int dictSize = 1;
        for(char c = 'A'; c <= 'Z'; c++) {
            dict.put(String.valueOf(c), dictSize++);
        }
        
        int i = 0;
        while(i < msg.length()) {
            String w = "";
            
            int j = i;
            while(j < msg.length() && dict.containsKey(w+msg.charAt(j))) {
                w += msg.charAt(j);
                j++;
            }
            
            //사전에 새로 저장
            if(j < msg.length()) {
                dict.put(w+msg.charAt(j), dictSize++);
            }
            
            //정답에 출력값 추가
            answer.add(dict.get(w));
            
            i += w.length();
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}