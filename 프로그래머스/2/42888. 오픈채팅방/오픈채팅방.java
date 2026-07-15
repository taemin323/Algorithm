import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        Map<String, String> map = new HashMap<>();
        String[] order = new String[record.length];
        String[] id = new String[record.length];
        List<String> result = new ArrayList<>();
        
        for(int i = 0; i < record.length; i++) {
            String[] parts = record[i].split(" ");
            
            order[i] = parts[0];
            id[i] = parts[1];
            
            if(!parts[0].equals("Leave")) {
                map.put(parts[1], parts[2]);
            }
        }
        
        for(int i = 0; i < record.length; i++) {
            if(order[i].equals("Change")) continue;
            
            if(order[i].equals("Leave")) {
                result.add(map.get(id[i]) + "님이 나갔습니다.");
            } else {
                result.add(map.get(id[i]) + "님이 들어왔습니다.");
            }
        }
        
        return result.stream().toArray(String[]::new);
    }
}