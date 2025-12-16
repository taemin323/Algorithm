import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int M;
	private static int[][] board;
	private static boolean[][] visited;
	private static int answer;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j) - '0';
			}
		}
		answer = Integer.MIN_VALUE;
		dfs(0,0);
		System.out.println(answer);
		
	}// end of main

	private static void dfs(int r, int c) {
		if(r == N) {
			calculate();// 결정된 모양으로 계산
			return;
		}
		
		// 다음 칸
		int nr = r;
		int nc = c + 1;
		if(nc == M) {
			nr = r + 1;
			nc = 0;
		}
		
		// 현재 칸을 가로 조각에 포함시키기(가로 : true)
		visited[r][c] = true;
		dfs(nr, nc);
		
		// 현재 칸을 세로 조각에 포함시키기(세로 : false)
		visited[r][c] = false;
		dfs(nr, nc);
		
	}

	private static void calculate() {
		int totalSum = 0;
		
		// 가로 방향으로 훑으면서 합 구하기
		for (int i = 0; i < N; i++) {
			int tmp = 0;
			for (int j = 0; j < M; j++) {
				if(visited[i][j]) {
					tmp *= 10;
					tmp += board[i][j];
				} else {// 세로 조각을 만나면 지금까지 만든 가로 숫자 더하고 초기화
					totalSum += tmp;
					tmp = 0;
				}
			}
			// 한 행이 끝났을 때 남은 숫자 더해주기
			totalSum += tmp;
		}
		
		// 세로 방향으로 훑으면서 합 구하기
		for (int j = 0; j < M; j++) {
			int tmp = 0;
			for (int i = 0; i < N; i++) {
				if(!visited[i][j]) {
					tmp *= 10;
					tmp += board[i][j];
				} else {
					totalSum += tmp;
					tmp = 0;
				}
			}
			totalSum += tmp;
		}
		
		answer = Math.max(answer, totalSum);
	}
}// end of class