import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N+1][N+1];
		
		// 길이 1
		for (int i = 1; i <= N; i++) {
			dp[i][i] = 1;
		}
		
		// 길이 2
		for (int i = 1; i < N; i++) {
			if(arr[i] == arr[i+1]) dp[i][i+1] = 1;
		}
		
		// 길이 3
		for (int len = 3; len <= N; len++) {
			for (int s = 1; s + len -1 <= N; s++) {
				int e = s + len - 1;
				if(arr[s] == arr[e] && dp[s+1][e-1] == 1) {
					dp[s][e] = 1;
				}
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			sb.append(dp[S][E]).append("\n");
		}
		System.out.println(sb.toString());
	}// end of main
}// end of class
