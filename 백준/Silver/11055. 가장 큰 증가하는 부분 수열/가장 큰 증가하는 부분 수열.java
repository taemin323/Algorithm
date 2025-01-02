import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N+1];
		
		//dp[i] = 부분 증가수열에서 i번째 항이 최댓값인 부분증가수열의 누적합
		int[] dp = new int[N+1];
		int max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[1] = arr[1];
		}//입력 완료
		
		for (int i = 1; i <= N; i++) {
			dp[i] = arr[i];
			for (int j = 1; j < i; j++) {
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[j]+arr[i], dp[i]);
				}
			}
		}
		
		for (int i : dp) {
			max = Math.max(max, i);
		}
		
		System.out.println(max);
	}//end of main
}//end of class
