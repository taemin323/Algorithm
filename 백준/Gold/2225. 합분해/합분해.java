import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int num = 1000000000;
		
		int[][] dp = new int[N+1][K+1];
		
		// 0개로 만들 수 있는 경우의 수 -> 0
		// 1개로 만들 수 있는 경우의 수 -> 1개
		for (int i = 0; i < N+1; i++) {
			dp[i][0] = 0;
			dp[i][1] = 1;
		}
		
		// N이 1일 경우 만들 수 있는 경우의 수 K개
		for (int i = 0; i < K+1; i++) {
			dp[1][i] = i;
		}
		
		for (int i = 2; i < N+1; i++) {
			for (int j = 2; j < K+1; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % num;
			}
		}
		
		System.out.println(dp[N][K]);
		
	}//end of main
}//end of class
