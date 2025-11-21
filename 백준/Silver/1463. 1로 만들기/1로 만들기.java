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
		
		System.out.println(recur(N));
		
	}// end of main

	private static int recur(int n) {
		
		if(dp[n] == null) {
			if(n % 6 == 0) {
				dp[n] = Math.min(recur(n-1), Math.min(recur(n / 3), recur(n / 2))) + 1;
			} else if(n % 3 == 0) {
				dp[n] = Math.min(recur(n / 3), recur(n-1)) + 1;
			} else if(n % 2 == 0) {
				dp[n] = Math.min(recur(n /2), recur(n-1)) + 1;
			} else {
				dp[n] = recur(n-1) + 1;
			}
			
		}
		
		return dp[n];
	}
}// end of class
