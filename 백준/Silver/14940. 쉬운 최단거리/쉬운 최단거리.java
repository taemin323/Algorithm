import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	
	
	
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[][] dist;
	private static boolean[][] visited;
	private static int[] dr = {0,0,-1,1};
	private static int[] dc = {-1,1,0,0};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dist = new int[N][M];
		visited = new boolean[N][M];
		
		int startR = -1;
		int startC = -1;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					startR = i;
					startC = j;
				}
				if (map[i][j] == 1) {
					dist[i][j] = -1; // 도달 불가능 경우도 있기 때문에 -1로 초기화.
				}
			}
		}//입력 완료
		
		bfs(startR, startC);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}// end of main

	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r, c});
		visited[r][c] = true;
		dist[r][c] = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			for (int d = 0; d < dc.length; d++) {
				int nr = curR + dr[d];
				int nc = curC + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(visited[nr][nc])continue;
				if(map[nr][nc] == 0) continue; // 원래 못가는 곳.
				
				visited[nr][nc] = true;
				dist[nr][nc] = dist[curR][curC] + 1;
				q.add(new int[] {nr, nc});
			}
		}
	}

	
}//end of class
