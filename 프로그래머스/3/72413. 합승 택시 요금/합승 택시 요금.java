import java.util.*;

class Solution {
    private static final int INF = Integer.MAX_VALUE;
    
    private static class Node implements Comparable<Node>{
        int to, cost;
        
        public Node() {
        }
        
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<Node>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        //양방향 그래프
        for(int[] fare : fares) {
            int startNode = fare[0];
            int endNode = fare[1];
            int cost = fare[2];
            
            graph.get(startNode).add(new Node(endNode, cost));
            graph.get(endNode).add(new Node(startNode, cost));
        }
        
        int[] minS = dijkstra(n, s, graph);
        int[] minA = dijkstra(n, a, graph);
        int[] minB = dijkstra(n, b, graph);
        
        int answer = INF;
        for(int i = 1; i <= n; i++) {
            if(minS[i] == INF || minA[i] == INF || minB[i] == INF) {
                continue;
            }
            
            int total = minS[i] + minA[i] + minB[i];
            answer = Math.min(answer, total);
        }
        return answer;
    }
    
    private int[] dijkstra(int n, int startNode, List<List<Node>> graph) {
        int[] minFare = new int[n+1];
        Arrays.fill(minFare, INF);
        minFare[startNode] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNode, 0));
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(cur.cost > minFare[cur.to]) continue;
            
            for(Node near : graph.get(cur.to)) {
                int newFare = cur.cost + near.cost;
                
                if(newFare < minFare[near.to]) {
                    minFare[near.to] = newFare;
                    pq.add(new Node(near.to, newFare));
                }
            }
        }
        return minFare;
    }
}