import java.util.*;
/**
* 최소 신장 트리(MST) 문제
* 크루스칼로 구해보자
* 크루스칼 알고리즘에서는 인접리스트 필요없음.
* 모든 간선을 가중치 오름차순으로 정렬
* 가중치가 작은 간선부터 하나씩 확인하면서, 사이클을 만들지 않으면 선택(union-find로 사이클 판정하자)
* 간선을 n-1개 선택하면 종료
*/

class Solution {
    int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n+1];
        
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        Arrays.sort(costs, (a,b) -> a[2] - b[2]);
        
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
    
    void union(int a, int b) {
        int pa = parent[a];
        int pb = parent[b];
        if(pa != pb) parent[pb] = pa;
    }
    
    int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}