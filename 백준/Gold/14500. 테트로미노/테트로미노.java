import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int[][] board;
	private static int answer;
	private static boolean[][] visited;
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력 완료
		
		answer = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, board[i][j], 1);
				visited[i][j] = false;
			}
		}
		System.out.println(answer);
	}// end of main

	private static void dfs(int i, int j, int sum, int cnt) {
		if(cnt == 4) {
			answer = Math.max(answer, sum);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			
			if(!visited[nr][nc]) {
				
				//ㅗ 모양은 2번째에서 다시 한번 탐색 과정을 거쳐야함.
				if(cnt == 2) {
					visited[nr][nc] = true;
					dfs(i, j, sum + board[nr][nc], cnt+1);
					visited[nr][nc] = false;
				}
				
				visited[nr][nc] = true;
				dfs(nr, nc, sum + board[nr][nc], cnt+1);
				visited[nr][nc] = false;
			}
		}
	}

	
}// end of class
