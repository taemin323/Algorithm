import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        int n = record.length;
        
        
        Map<String, String> map = new HashMap<>();
        String[] type = new String[n];
        String[] id = new String[n];
        List<String> result = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            String[] parts = record[i].split(" ");
            type[i] = parts[0];
            id[i] = parts[1];
            
            if(type[i].equals("Leave")) continue;
            
            map.put(parts[1], parts[2]);
        }
        
        for(int i = 0; i < n; i++) {
            if(type[i].equals("Change")) continue;
            
            if(type[i].equals("Enter")) {
                result.add(map.get(id[i]) + "님이 들어왔습니다.");
            } else {
                result.add(map.get(id[i]) + "님이 나갔습니다.");
            }
        }
        return result.stream().toArray(String[]::new);
    }
}