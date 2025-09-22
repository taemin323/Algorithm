import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 1 <= N <= 100,000
 * 1 <= M <= N
 * 0 <= x <= N
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[] arr = new int[M];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		int ans = 0;
		ans = Math.max(ans, arr[0] - 0);
		
		for (int i = 1; i < M; i++) {
			int dist = arr[i] - arr[i-1];
			ans = Math.max(ans, (dist+1)/2);
		}
		
		ans = Math.max(ans, N - arr[M-1]);
		
		System.out.println(ans);
	}// end of main
}// end of class
