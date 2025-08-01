import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 1 <= N <= 50,00 (정점 개수)
 * 1 <= M <= 50,000 (간선 개수)
 * 0 <= C <= 1,000 (갼선 가중치)
 */
public class Main {
	static class Node implements Comparable<Node>{
		int v, cost;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	private static List<Node>[] adj;
	private static int N;
	private static int M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			adj[A].add(new Node(B, C));
			adj[B].add(new Node(A, C));
		}// 입력 완료
		
		int ans = dijkstra(1);
		System.out.println(ans);
	}//end of main

	private static int dijkstra(int start) {
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.cost > dist[cur.v]) {
				continue;
			}
			
			for(Node next: adj[cur.v]) {
				int newCost = cur.cost + next.cost;
				if(newCost < dist[next.v]) {
					dist[next.v] = newCost;
					pq.offer(new Node(next.v, newCost));
				}
			}
		}
		return dist[N];
	}
}//end of class
