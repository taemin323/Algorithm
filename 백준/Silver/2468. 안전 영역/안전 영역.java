import java.util.*;
import java.io.*;

public class Main {
	

	private static int N;
	private static int[][] map;
	private static int minH;
	private static int maxH;
	private static int answer = 1;
	private static int[] dr = {0,0,-1,1};
	private static int[] dc = {-1,1,0,0};
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		minH = Integer.MAX_VALUE;
		maxH = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				minH = Math.min(minH, map[i][j]);
				maxH = Math.max(maxH, map[i][j]);
			}
		}
		
		for (int h = minH; h <= maxH; h++) {
			visited = new boolean[N][N];
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > h && !visited[i][j]) {
						bfs(i,j,h);
						cnt++;
					}
				}
			}
			
			answer = Math.max(answer, cnt);
		}
		
		System.out.println(answer);
	}// end of main

	private static void bfs(int i, int j, int h) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i,j});
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = curR + dr[d];
				int nc = curC + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if(map[nr][nc] <= h || visited[nr][nc]) continue;
				
				q.add(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
		
	}
}// end of class