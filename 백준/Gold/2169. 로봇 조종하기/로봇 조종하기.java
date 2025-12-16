import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//첫번째 행
		dp[0][0] = map[0][0];
		for (int j = 1; j < M; j++) {
			dp[0][j] = dp[0][j-1] + map[0][j];
		}
		
		// 나머지 행
		for (int i = 1; i < N; i++) {
			
			// 임시 배열 2개 생성
			int[] leftDp = new int[M];
			int[] rightDp = new int[M];
			
			leftDp[0] = dp[i-1][0] + map[i][0];
			for (int j = 1; j < M; j++) {
				// 위 vs 왼쪽
				leftDp[j] = Math.max(dp[i-1][j], leftDp[j-1]) + map[i][j];
			}
			
			rightDp[M-1] = dp[i-1][M-1] + map[i][M-1];
			for (int j = M-2; j >= 0; j--) {
				// 위 vs 오른쪽
				rightDp[j] = Math.max(dp[i-1][j], rightDp[j+1]) + map[i][j];
			}
			
			for (int j = 0; j < M; j++) {
				dp[i][j] = Math.max(leftDp[j], rightDp[j]);
			}
		}
		System.out.println(dp[N-1][M-1]);
		
	}// end of main

	
}// end of class