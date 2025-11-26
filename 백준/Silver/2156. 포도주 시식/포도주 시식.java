import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] wine = new int[N+1];
		int[] dp = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}// 입력 완료
		
		// dp[i] = i까지 최대로 많이 마신 포도주 총량
		dp[1] = wine[1];
		
		if(N == 1) {
			System.out.println(dp[1]);
			return;
		}
		
		dp[2] = wine[1] + wine[2];
		
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + wine[i], dp[i-3] + wine[i-1] + wine[i]));
		}
		System.out.println(dp[N]);
	}// end of main
}// end of class
