import java.util.*;
/**
* 간선의 개수는 n-1
*/

class Solution {
    int[] parent;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        Arrays.sort(costs, (a,b) -> a[2] - b[2]);
        
        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        int cnt = 0;
        for(int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int cost = costs[i][2];
            
            if(find(from) != find(to)) {
                union(from, to);
                answer += cost;
                cnt++;
            }
            
            if(cnt == n-1) break;
        }
        
        return answer;
    }
    
    int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB) parent[rootB] = rootA;
    }
}