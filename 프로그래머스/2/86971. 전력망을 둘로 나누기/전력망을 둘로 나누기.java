import java.util.*;

class Solution {
    
    List<List<Integer>> tree;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        // 트리 생성
        tree = new ArrayList<>();
        for(int i= 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        
        // 트리 구성
        for(int i = 0; i < wires.length; i++) {
            int from = wires[i][0];
            int to = wires[i][1];
            
            // 양방향
            tree.get(from).add(to);
            tree.get(to).add(from);
        }
        
        // 완전 탐색
        for(int i = 0; i < wires.length; i++) {
            int curFrom = wires[i][0];
            int curTo = wires[i][1];
            
            // 간선 끊기
            tree.get(curFrom).remove(Integer.valueOf(curTo));
            tree.get(curTo).remove(Integer.valueOf(curFrom));
            
            boolean[] visited = new boolean[n+1];
            int cnt = 0;
            for(int j = 1; j <= n; j++) {
                if(!visited[j]) {
                    bfs(j, visited);
                    break;
                }
                
            }
            
            //차이 계산
            int group1 = 0;
            for(int k = 1; k <= n; k++) {
                if(visited[k]) group1++;
            }
            int group2 = n - group1;
            
            answer = Math.min(answer, Math.abs(group1-group2));
            
            //원복
            Arrays.fill(visited, false);
            tree.get(curFrom).add(curTo);
            tree.get(curTo).add(curFrom);
        }
        return answer;
    }
    
    void bfs(int idx, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(idx);
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            visited[cur] = true;
            
            for(int i : tree.get(cur)) {
                if(!visited[i]) {
                    q.add(i);
                }
            }
        }
    }
}