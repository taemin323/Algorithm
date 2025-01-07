import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int[][] board;
	private static int[][] dr = {{0,1}, {0,1}, {-1,0}, {-1,0}};
	private static int[][] dc = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	private static boolean[][] visited;
	private static int max;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());//세로
		M = Integer.parseInt(st.nextToken());//가로
		
		board = new int[N][M];
		visited = new boolean[N][M];
		
		max = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}//입력 완료
		
		dfs(0,0);
		System.out.println(max);
	}//end of main

	private static void dfs(int tmp, int sum) {
		if(tmp == N*M) {
			max = Math.max(max, sum);
			return;
		}
		
		int r = tmp/M;
		int c = tmp%M;
		
		
		if(!visited[r][c]) {
			for (int d = 0; d < dc.length; d++) {
				int i1 = r + dr[d][0];
				int i2 = r + dr[d][1];
				int j1 = c + dc[d][0];
				int j2 = c + dc[d][1];
				
				if(isPossible(i1,i2,j1,j2)) {
					visited[r][c] = true;
					visited[i1][j1] = true;
					visited[i2][j2] = true;
					dfs(tmp+1, sum+(board[r][c]*2)+board[i1][j1]+board[i2][j2]);
					visited[r][c] = false;
					visited[i1][j1] = false;
					visited[i2][j2] = false;
				}
			}
		}
		dfs(tmp+1, sum);
	}

	private static boolean isPossible(int i1, int i2, int j1, int j2) {
		if(i1<0 || i2<0 || i1>=N || i2>=N) return false;

		if(j1<0 || j2<0 || j1>=M || j2>=M) return false;
		
		if(visited[i1][j1]) return false;

		if(visited[i2][j2]) return false;
		
		return true;
	}
}//end of class
