import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.management.openmbean.ArrayType;

public class Main {
	static class Node implements Comparable<Node>{
		int idx, distance;
		
		public Node(int idx, int distance) {
			super();
			this.idx = idx;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}
	
	private static int N;
	private static int M;
	private static int X;
	private static ArrayList<ArrayList<Node>> list, reverseList;
	private static int[] dist;
	private static int[] reverseDist;
	private static int ans = -1;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());//N개의 마을 = N명의 학생
		M = Integer.parseInt(st.nextToken());//M개의 단방향 도로들
		X = Integer.parseInt(st.nextToken());//1<=X<=N
		
		list = new ArrayList<>();
		reverseList = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
			reverseList.add(new ArrayList<>());
		}
		
		dist = new int[N+1];
		reverseDist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(reverseDist, Integer.MAX_VALUE);
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list.get(s).add(new Node(e, cost));
			reverseList.get(e).add(new Node(s, cost));
		}
		
		dijkstra(list, dist, X);
		dijkstra(reverseList, reverseDist, X);
		
		for (int i = 1; i <= N; i++) {
			ans = Math.max(ans, dist[i]+reverseDist[i]);
		}
		System.out.println(ans);
		
	}//end of main

	private static void dijkstra(ArrayList<ArrayList<Node>> list, int[] distance, int start) {
		boolean[] visited = new boolean[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			int idx = pq.poll().idx;
			
			if(visited[idx]) {
				continue;
			}
			
			visited[idx] = true;
			
			for (Node node : list.get(idx)) {
				if(distance[node.idx]> distance[idx]+node.distance) {
					distance[node.idx] = distance[idx]+node.distance;
					pq.add(new Node(node.idx, distance[node.idx]));
				}
			}
		}
	}
}//end of class
