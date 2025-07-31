import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int T;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}// 입력 완료
		
		int result = bfs(0,0);
		
		System.out.println(result <= T ? result : "Fail");
		
	}// end of main

	private static int bfs(int r, int c) {
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r, c});
		visited[r][c] = true;
		
		int time = 0;
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			
			for (int i = 0; i < qSize; i++) {
				int[] cur = q.poll();
				int curR = cur[0];
				int curC = cur[1];
				
				if(time > T) continue;
				
				if(curR == N - 1 && curC == M - 1) {
				 min = Math.min(min, time);
				 return min;
				}
				
				if(map[curR][curC] == 2) {
					int direct = time + (N - 1 - curR) + (M - 1 - curC);
					min = Math.min(min, direct);
				}
				
				for (int d = 0; d < dc.length; d++) {
					int nr = curR + dr[d];
					int nc = curC + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
					
					if(map[nr][nc] != 1) {
						visited[nr][nc] = true;
						q.add(new int[] {nr, nc});
					}
				}
			}
			time++;
		}
		return min;
	}
}// end of class
