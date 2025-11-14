import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		long M = Integer.parseInt(st.nextToken());
		
		int[] trees = new int[N];
		int max = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, trees[i]);
		}// 입력 완료
		
		Arrays.sort(trees);
		int answer = 0;
		int left = 0;
		int right = max;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			long sum = 0;
			
			for(int i = 0; i < N; i++) {
				if(trees[i] > mid) {
					sum += trees[i] - mid;
				}
			}
			
			if(sum >= M) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(answer);
	}// end of main
}// end of class
