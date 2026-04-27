import java.util.*;

class Solution {
    List<String> list = new ArrayList<>();
    boolean[] visited;
    boolean flag;
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        
        Arrays.sort(tickets, (a,b) -> a[1].compareTo(b[1]));
        
        list.add("ICN");
        dfs(0, "ICN", tickets);
        return list.stream().toArray(String[]::new);
    }
    
    void dfs(int depth, String str, String[][] tickets) {
        if(flag) return;
        
        if(depth == tickets.length) {
            flag = true;
            return;
        }
        
        for(int i = 0; i < tickets.length; i++) {
            if(!visited[i] && tickets[i][0].equals(str)) {
                visited[i] = true;
                list.add(tickets[i][1]);
                dfs(depth+1, tickets[i][1], tickets);
                
                if(flag) return;
                visited[i] = false;
                list.remove(list.size()-1);
            }
        }
    }    
}