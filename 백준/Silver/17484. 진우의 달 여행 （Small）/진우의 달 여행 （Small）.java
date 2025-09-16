import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Point {
		int r, c, prevDir;
		
		public Point(int r, int c, int prevDir) {
			this.r = r;
			this.c = c;
			this.prevDir = prevDir;
		}
	}
	
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[][][] memo;
	private static int[] dc = {-1,0,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//입력 완료
		
		memo = new int[N][M][4];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		
		int ans = Integer.MAX_VALUE;
		// 시작은 0행 어느 열이든 가능, 시작 직전 방향은 -1로 고정.
		for (int i = 0; i < M; i++) {
			ans = Math.min(ans, dfs(new Point(0,i,-1)));
		}
		System.out.println(ans);
	}// end of main

	private static int dfs(Point cur) {
		// prevDir이 있을 때만 메모 사용
	    if (cur.prevDir >= 0 && memo[cur.r][cur.c][cur.prevDir] != -1) {
	        return memo[cur.r][cur.c][cur.prevDir];
	    }
		
		if(cur.r == N -1) return map[cur.r][cur.c];
		
		int best = Integer.MAX_VALUE;
		for (int d = 0; d < 3; d++) {
			if(d == cur.prevDir) continue;
			int nr = cur.r + 1;
			int nc = cur.c + dc[d];
			
			if(nc < 0 || nc >= M) continue;
			
			best = Math.min(best, map[cur.r][cur.c]+ dfs(new Point(nr, nc, d)));
		}
		
		// prevDir이 있을 때만 기록
		if (cur.prevDir >= 0) memo[cur.r][cur.c][cur.prevDir] = best;
		return best;
	}
}// end of class
