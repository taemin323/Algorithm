import java.util.*;
/**
* 25분 -> 실패
* 가장 멀리 떨어지 노드란 최단경로로 이동했을 때 간선의 개수
* 간선의 가중치는 없음.
* 출발지도 고정. 다익스트라로 풀면 됨.
* max 구하고 그 max와 일치하는 dist의 인덱스 카운팅하기.
*/

class Solution {
    int answer = 0;
    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new ArrayList[n+1];
        
        for(int i = 0; i < n+1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edge.length; i++) {
            int from = edge[i][0];
            int to = edge[i][1];
            
            graph[from].add(to);
            graph[to].add(from);
        }
        
        List<int[]> result = dijkstra(1, n, graph);
        
        Collections.sort(result, (a,b) -> b[1] - a[1]);
        for(int i = 0; i < result.size(); i++) {
            if(result.get(0)[1] == result.get(i)[1]) answer++;
        }
        
        return answer;
    }
    
    List<int[]> dijkstra(int start, int n, List<Integer>[] graph) {
        List<int[]> result = new ArrayList<>();
        boolean[] visited = new boolean[n+1];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.add(new int[] {start, 0});
        visited[start] = true;
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            
            result.add(new int[] {curNode, curDist});
                        
            for(int i : graph[curNode]) {
                if(!visited[i]) {
                    visited[i] = true;
                    pq.add(new int[] {i, curDist+1});
                }
            }
            
        }
        
        return result;
    }
}