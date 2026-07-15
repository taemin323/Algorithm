import java.util.*;

class Solution {
    List<String> list = new ArrayList<>();
    boolean[] visited;
    boolean flag;
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        list.add("ICN");
        
        Arrays.sort(tickets, (a,b) -> a[1].compareTo(b[1]));
        
        dfs("ICN", tickets, 0);
        
        return list.toArray(new String[0]);
    }
    
    void dfs(String cur, String[][] tickets, int depth) {
        if(depth == tickets.length) {
            flag = true;
            return;
        }
        
        for(int i = 0; i < tickets.length; i++) {
            if(!visited[i] && tickets[i][0].equals(cur)) {
                visited[i] = true;
                list.add(tickets[i][1]);
                dfs(tickets[i][1], tickets, depth+1);
                
                if(flag) return;
                visited[i] = false;
                list.remove(list.size()-1);
            }
        }
    }
}