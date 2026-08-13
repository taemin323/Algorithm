import java.util.*;
/**
* 다익스트라는 항상 s로부터 모든 정점까지의 최단거리를 계산
* s -> t는 같이 가고, t -> a, t -> b는 따로 구하기
* 즉 s에서 t까지의 최단 거리, a에서 t까지의 최단 거리, b에서 t까지의 최단거리만 구하면 됨.
* 
*/

class Solution {
    List<int[]>[] graph;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList[n+1];
        
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < fares.length; i++) {
            int from = fares[i][0], to = fares[i][1], cost = fares[i][2];
            
            graph[from].add(new int[] {to, cost});
            graph[to].add(new int[] {from, cost});
        }
        
        int[] distS = dijkstra(n, s);
        int[] distA = dijkstra(n, a);
        int[] distB = dijkstra(n, b);
        
        for(int t = 1; t <= n; t++) {
            if(distS[t] == Integer.MAX_VALUE || distA[t] == Integer.MAX_VALUE || distB[t] == Integer.MAX_VALUE) continue;
            answer = Math.min(answer, distS[t] + distA[t] + distB[t]);
        }
        return answer;
    }
    
    int[] dijkstra(int n, int start) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.add(new int[] {start, 0});
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];
            
            if(curCost > dist[curNode]) continue;
            
            for(int[] next : graph[curNode]) {
                int newCost = curCost + next[1];
                if(newCost < dist[next[0]]) {
                    dist[next[0]] = newCost;
                    pq.add(new int[] {next[0], newCost});
                }
            }
        }
        return dist;
    }
}