import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int r, c, cost;
		
		public Node() {
		}

		public Node(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return cost - o.cost;
		}
	}
	
	private static int n;
	private static int[][] map;
	private static int[][] dist;
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());//방의 수
		
		map = new int[n][n];
		dist = new int[n][n];
		
		//0은 검은 방, 1은 흰 방
		for (int r = 0; r < n; r++) {
			String str = br.readLine();
			for (int c = 0; c < n; c++) {
				map[r][c] = str.charAt(c) - '0' == 1 ? 0 : 1;
				dist[r][c] = Integer.MAX_VALUE;
			}
		}//입력 완료
		bfs();
		System.out.println(dist[n-1][n-1]);
		
	}//end of main

	private static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0,0,0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			for (int d = 0; d < dc.length; d++) {
				int nr = node.r + dr[d];
				int nc = node.c + dc[d];
				
				if(nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] > (map[nr][nc]+ node.cost)) {
					dist[nr][nc] = map[nr][nc] + node.cost;
					pq.offer(new Node(nr, nc, dist[nr][nc]));
				}
			}
		}
		
	}

	
}//end of class
