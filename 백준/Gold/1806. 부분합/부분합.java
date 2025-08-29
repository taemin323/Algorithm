import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 10 <= N <= 100,000
 * 0 <= S <= 100,000,000 (연속된 수들의 부분합)
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//입력 완료
		
		int ans = Integer.MAX_VALUE;
		int sum = 0;
		int left = 0;
		int right = 0;
		
		while(true) {
			if(sum >= S) {
				ans = Math.min(ans, right - left);
				sum -= arr[left++];
			} else {
				if(right == N) break;
				sum += arr[right++];
			}
		}
		System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
	}// end of main
}// end of class
