import java.util.*;

class Solution {
    class Node implements Comparable<Node>{
        int node, intensity;
        
        public Node(int node, int intensity) {
            this.node = node;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(Node o) {
            return intensity - o.intensity;
        }
        
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        List<Node>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < paths.length; i++) {
            int node1 = paths[i][0];
            int node2 = paths[i][1];
            int intensity = paths[i][2];
            
            graph[node1].add(new Node(node2, intensity));
            graph[node2].add(new Node(node1, intensity));
        }
    
        // dist[i] : 출발지 - i까지 오는 경로 중 최대 간선 가중치들 중에서의 최솟값
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        //다익스트라
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        Set<Integer> gateSet = new HashSet<>();
        for(int gate : gates) gateSet.add(gate);
        
        Set<Integer> summitSet = new HashSet<>();
        for(int summit : summits) summitSet.add(summit);
        
        for (int gate : gates) {
            dist[gate] = 0;
            pq.offer(new Node(gate, 0));
        }
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dist[cur.node] < cur.intensity) continue;
            if(gateSet.contains(cur.node) && cur.intensity != 0) continue;
            if(summitSet.contains(cur.node)) continue;
            
            for(Node next : graph[cur.node]) {
                int newIntensity = Math.max(cur.intensity, next.intensity);
                
                if(newIntensity < dist[next.node]) {
                    dist[next.node] = newIntensity;
                    pq.offer(new Node(next.node, newIntensity));
                }
            }
        }
        
        int[] answer = new int[2];
        
        Arrays.sort(summits);
        int min = Integer.MAX_VALUE;
        int minSummit = 0;
        for(int i = summits.length - 1; i >= 0; i--) {
            if(dist[summits[i]] <= min) {
                min = dist[summits[i]];
                minSummit = summits[i];
            }            
        }
        
        answer[0] = minSummit;
        answer[1] = min;
        
        
        return answer;
    }
}