import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Point implements Comparable<Point> {
		int s, e, cost;
		
		public Point() {
		}

		public Point(int s, int e, int cost) {
			super();
			this.s = s;
			this.e = e;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}
		
		
	}

	private static int N;
	private static int D;
	private static int[] distance;
	private static Point[] graph;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		distance = new int[D + 1];
		for (int i = 0; i < distance.length; i++) {
			distance[i] = i;
		}
		
		graph = new Point[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			graph[i] = new Point(start, end, dist);
		}
		
		dijkstra(0);
		System.out.println(distance[D]);
	}//end of main

	private static void dijkstra(int start) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(start, 0, 0));
		
		distance[start] = 0;
		
		while (!pq.isEmpty()) {
			Point curr = pq.poll();
			int curSpot = curr.e;
			
			//현재 위치에서 도달 가능한 길들 확인
			for (Point p : graph) {
				if(p.s >= curSpot) {// 시작 위치가 현재 위치보다 뒤족에 있을 때
					if (p.e > D) continue;
					
					if(distance[p.e] > distance[curSpot] + p.cost + (p.s - curSpot)) {
						distance[p.e] = distance[curSpot] + p.cost + (p.s - curSpot);
						pq.add(new Point(curSpot, p.e, distance[p.e]));
					}
				}
			}
			distance[D] = Math.min(distance[curSpot] + D - curSpot, distance[D]);
		}
	}
}//end of class
