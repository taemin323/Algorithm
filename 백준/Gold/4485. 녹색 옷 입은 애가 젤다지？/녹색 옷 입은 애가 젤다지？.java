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
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return cost - o.cost;
		}
	}

	private static int N;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[][] size;
	private static int[] dr = {0,0,-1,1};
	private static int[] dc = {-1,1,0,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = null;
		int count = 1;
		
		while(!(str = br.readLine()).equals("0")) {
			 N = Integer.parseInt(str);
			if(N == 0) {
				break;
			}
			
			map = new int[N][N];
			visited = new boolean[N][N];
			size = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					size[r][c] = Integer.MAX_VALUE;
				}
			}// 입력 완료
			
			bfs(0,0, map[0][0]);
			sb.append("Problem ").append(count).append(": ").append(size[N-1][N-1]).append("\n");
			count++;
		}
		System.out.println(sb.toString());
	}// end of main

	private static void bfs(int r, int c, int cost) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		visited[r][c] = true;
		q.offer(new Node(r, c, cost));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			for (int d = 0; d < dc.length; d++) {
				int nr = node.r + dr[d];
				int nc = node.c + dc[d];
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && size[nr][nc] > (map[nr][nc] + node.cost)) {
					size[nr][nc] = map[nr][nc] + node.cost;
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc, size[nr][nc]));
				}
			}
		}
	}
}// end of class
