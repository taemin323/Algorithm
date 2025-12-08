import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int min = Integer.MAX_VALUE;
	private static int[] row;
	private static int[] column;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        int[][] board = new int[N][N];
        row = new int[N];
        column = new int[N];
        int totalSum = 0;
        
        for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				totalSum += board[i][j];
			}
		}
        
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				row[i] += board[i][j];
				column[i] += board[j][i];
			}
		}
        
        dfs(0,0,totalSum);
        System.out.println(min);
    }// end of main

	private static void dfs(int idx, int cnt, int cur) {
		if(cnt > N-1) {
			return;
		}
		
		min = Math.min(min, Math.abs(cur));
		
		if(idx > N-1) {
			return;
		}
		
		dfs(idx+1, cnt+1, cur - row[idx] - column[idx]);
		dfs(idx+1, cnt, cur);
	}

}// end of class