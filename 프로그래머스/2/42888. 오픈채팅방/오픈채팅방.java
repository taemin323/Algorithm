import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        //Map으로 id, 닉네임 관리
        Map<String, String> map = new HashMap<>();
        
        String[] type = new String[record.length];
        String[] id = new String[record.length];
        
        for(int i = 0; i < record.length; i++) {
            String[] parts = record[i].split(" ");
            
            type[i] = parts[0];
            id[i] = parts[1];
            
            if(type[i].equals("Enter") || type[i].equals("Change")) {
                map.put(id[i], parts[2]);
            } 
        }
        
        List<String> result = new ArrayList<>();
        
        for(int i = 0; i < record.length; i++) {
            if(type[i].equals("Enter")) {
                result.add(map.get(id[i]) + "님이 들어왔습니다.");
            }
            
            if(type[i].equals("Leave")) {
                result.add(map.get(id[i]) + "님이 나갔습니다.");
            }
        }
        
        return result.toArray(new String[0]);
    }
}