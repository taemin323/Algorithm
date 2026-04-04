import java.util.*;
import java.io.*;

public class Main {
	
	private static class Node implements Comparable<Node>{
		int to, cost;
		
		public Node(int near, int cost) {
			this.to = near;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	private static int n;
	private static int m;
	private static List<List<Node>> graph;
	private static int[][] answer;
	private static int[][] dist;
	private static int INF = 100_000_000;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Node(v, cost));
			graph.get(v).add(new Node(u, cost));
		}
		
		answer = new int[n+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			int[] arr = dijkstra(i);
			for(int j = 1; j <= n; j++) {
				if(i == j) {
					sb.append("-").append(" ");
				} else {
					sb.append(arr[j]).append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}// end of main

	private static int[] dijkstra(int s) {
		int[] dist = new int[n+1];
		int[] firstPoint = new int[n+1];
		
		Arrays.fill(dist, INF);
		dist[s] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(s, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int curIdx = cur.to;
			
			if(cur.cost > dist[curIdx]) continue;
			
			for(Node near : graph.get(curIdx) ) {
				int newCost = dist[curIdx] + near.cost;
				
				//더 짧은 경로를 발견했다면
				if(newCost < dist[near.to]) {
					dist[near.to] = newCost;
					
					if(curIdx == s) {
						// 시작점에서 바로 다음 칸으로 가는 경우
						// 첫 번째 경유지는 바로 그 인접 정점 자신이 됨
						firstPoint[near.to] = near.to;
					} else {
						// 이미 누군가를 거쳐서 온 경우
						// 시작점에서 curIdx까지 올  때 거쳤던 '첫 번째 정점'을 그대로 물려받음.
						firstPoint[near.to] = firstPoint[curIdx];
					}
					
					pq.add(new Node(near.to, newCost));
				} 
			}
		}
		
		return firstPoint;
	}
}// end of class