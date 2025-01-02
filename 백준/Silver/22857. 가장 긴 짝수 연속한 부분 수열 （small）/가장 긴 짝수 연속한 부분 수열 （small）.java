import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		//dp[i][j] = 처음부터 i번째 원소까지 고려했을 때, 최대 j개의 홀수를 제거하여 얻을 수 있는
		//가장 긴 연속된 짝수 부분 수열의 길이
		int[][] dp = new int[N+1][K+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		int max = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				if(arr[i-1] % 2 == 0) {
					dp[i][j] = dp[i-1][j] + 1;
				} else {
					if(j>0) {
						dp[i][j] = dp[i-1][j-1];
					}
				}
				max = Math.max(max, dp[i][j]);
			}
		}
		System.out.println(max);
	}//end of main
}//end of class
