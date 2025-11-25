import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] step = new int[N+1];
		for (int i = 1; i <= N; i++) {
			step[i] = Integer.parseInt(br.readLine());
		}// 입력 완료
		
		if(N == 1) {
			System.out.println(step[1]);
			return;
		}
		
		// dp[i] = i번째 계단에 도달할 수 있는 최대 점수
		int[] dp = new int[N+1];
		
		dp[1] = step[1];
		dp[2] = dp[1] + step[2];
		
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i-2] + step[i], dp[i-3] + step[i-1] + step[i]);
		}
		
		System.out.println(dp[N]);
		
	}// end of main
}// end of class
