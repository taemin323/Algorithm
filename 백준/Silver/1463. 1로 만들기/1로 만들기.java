import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static Integer[] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// dp[i] = 숫자 i를 1로 만드는 최소 연산 횟수
		dp = new Integer[N+1];
		dp[0] = dp[1] = 0;
		
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i-1] + 1;
			if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
		}
		
		System.out.println(dp[N]);
	}// end of main
}// end of class
