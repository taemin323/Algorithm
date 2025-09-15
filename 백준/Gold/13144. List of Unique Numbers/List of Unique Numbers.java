import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[] arr;
	private static long ans;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		visited = new boolean[100001];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		ans = 0;
		
		windowSliding();
		System.out.println(ans);
	}// end of main

	private static void windowSliding() {
		int left = 0;
		int right = 0;
		
		while(left < N) {
			while(right < N && !visited[arr[right]]) {
				visited[arr[right]] = true;
				ans += right - left + 1;
				right++;
			}
			visited[arr[left]] = false;
			left++;
		}
	}
}// end of class
