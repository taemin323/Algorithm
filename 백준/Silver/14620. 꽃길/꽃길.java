import java.io.*;
import java.util.*;

public class Main {
	
	private static int N;
	private static int[][] map;
	private static boolean[][] visited;
	private static int answer = Integer.MAX_VALUE;
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0);
		
		System.out.println(answer);
	}// end of main	

	private static void dfs(int depth, int cost) {
		if(depth == 3) {
			answer = Math.min(answer, cost);
			return;
		}
		
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < N-1; j++) {
				//현재 위치+ 상하좌우에 꽃잎이 있으면 안된다.
				if(visited[i][j]) continue;
				boolean flag = true;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					if(visited[nr][nc]) {
						flag = false;
						break;
					}
				}
				
				if(!flag) continue;
				
				//방문 처리
				int sum = map[i][j];
				visited[i][j] = true;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					sum += map[nr][nc];
					visited[nr][nc] = true;
				}
				
				dfs(depth+1, cost+sum);
				
				//원복
				visited[i][j] = false;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					visited[nr][nc] = false;
				}
			}
		}
	}
}// end of class
