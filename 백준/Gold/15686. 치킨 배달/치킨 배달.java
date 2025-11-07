import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Point {
		int r, c;
		
		public Point() {
		}
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	private static int N;
	private static int M;
	private static int[][] map;
	private static ArrayList<Point> storeList;
	private static boolean[] visited;
	private static int min;
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};
	private static int[][] memo;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		
		map = new int[N][N];
		storeList = new ArrayList<Point>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 2) {
					storeList.add(new Point(i,j));
				}
			}
		}// 입력 완료
		
		visited = new boolean[storeList.size()];
		min = Integer.MAX_VALUE;
		comb(0,0);
		
		System.out.println(min);
	}// end of main

	private static void comb(int idx, int cnt) {
		if(cnt == M) {
			calculate();
		}
		
		for (int i = idx; i < storeList.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				comb(i+1, cnt+1);
				visited[i] = false;
			}
		}
	}

	private static void calculate() {
		int sum = 0;
		
		memo = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1) {
					memo[i][j] = 1;
				}
			}
		}
		
		for (int i = 0; i < storeList.size(); i++) {
			if(visited[i]) {
				Point curStore = storeList.get(i);
				
				int curR = curStore.r;
				int curC = curStore.c;
				
				memo[curR][curC] = 2;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(memo[i][j] == 1) {
					int curDist = bfs(i,j);
					sum += curDist;
				}
			}
		}
		
		min = Math.min(min, sum);
	}

	private static int bfs(int i, int j) {
		boolean[][] isVisited = new boolean[N][N];
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i, j});
		
		int dist = 0;
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			for (int k = 0; k < qSize; k++) {
				int[] cur = q.poll();
				
				int curR = cur[0];
				int curC = cur[1];
				
				if(memo[curR][curC] == 2) {
					return dist;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr = curR + dr[d];
					int nc = curC + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					if(!isVisited[nr][nc]) {
						isVisited[nr][nc] = true;
						q.add(new int[] {nr, nc});
					}
				
				}
			}
			dist++;
		}
		
		return dist;
	}
}// end of class
