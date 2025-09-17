import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 3 <= N <= 10,000
 * N <= M <= 1,000,000,000
 * 1 <= 예산요청 값 <= 100,000
 */
public class Main {
	private static int N;
	private static int[] areas;
	private static int M;
	private static int sum;
	private static int max;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		areas = new int[N];
		
		sum = 0;
		max = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			areas[i] = Integer.parseInt(st.nextToken());
			sum += areas[i];
			max = Math.max(max, areas[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		// 예산요청의 합이 총 예산액보다 작다면 가장 큰 값이 그대로 출력.
		if (M >= sum) {
			System.out.println(max);
			return;
		}
		
		// 그게 아니라면 상한액이 출력.
		System.out.println(binarySearch());
		
		
	}// end of main

	private static int binarySearch() {
		int left = 0, right = max; 
		int ans = 0;
		
		while(left <= right) {
			int mid = (left + right) >>> 1;
			long used = 0;
			for (int area : areas) {
				used += (area < mid ? area : mid);
			}
			
			if(used <= M) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}

	
}// end of class
