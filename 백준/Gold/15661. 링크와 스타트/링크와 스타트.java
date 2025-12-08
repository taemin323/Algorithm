import java.io.*;
import java.util.*;

public class Main {
	

	private static int N;
	private static boolean[] visited;
	private static int[][] board;
	private static int answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MAX_VALUE;
		subset(0);
		System.out.println(answer);
	}// end of main	

	private static void subset(int idx) {
		if(idx == N) {
			calculate();
			return;
		}
		
		visited[idx] = true;
		subset(idx+1);
		
		visited[idx] = false;
		subset(idx+1);
	}

	private static void calculate() {
		int start = 0;
		int link = 0;
		
		boolean startEmpty = true;
		boolean linkEmpty = true;
		
		// 팀이 비어있는 경우 제외
		for (int i = 0; i < N; i++) {
			if(visited[i]) startEmpty = false;
			else linkEmpty = false;
		}
		if(startEmpty || linkEmpty) return;
		
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				if(visited[i] && visited[j]) {
					start += board[i][j] + board[j][i];
				} else if(!visited[i] && !visited[j]) {
					link += board[i][j] + board[j][i];
				}
			}
		}
		answer = Math.min(answer, Math.abs(start - link));
	}
}// end of class
