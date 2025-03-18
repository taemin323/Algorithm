import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());// 테스트 케이스 개수
		int[][] dp = new int[10001][4];
		dp[1][1] = 1;// 1
		dp[2][1] = 1;// 1+1
		dp[2][2] = 1;// 2
		dp[3][1] = 1;// 1+1+1
		dp[3][2] = 1;// 1+2
		dp[3][3] = 1;// 3
		
		for (int i = 4; i < 10001; i++) {
			dp[i][1] = dp[i-1][1];
			dp[i][2] = dp[i-2][1] + dp[i-2][2];
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
		}
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N][1] + dp[N][2] + dp[N][3]).append("\n");
		}// end of tc
		System.out.println(sb.toString());
	}// end of main
}// end of calss
