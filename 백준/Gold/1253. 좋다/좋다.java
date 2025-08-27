import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 1 <= N <= 2,000
 * -1,000,000,000 <= 수 <= 1,000,000,000
 * 수의 위치가 다르면 값이 같아도 다른 수이다.
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		Arrays.sort(arr);
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			int left = 0;
			int right = N-1;
			while(left < right) {
				if(left == i) {
					left++;
					continue;
				}
				if(right == i) {
					right--;
					continue;
				}
				
				long sum = arr[left] + arr[right];
				
				if(sum == arr[i]) {
					cnt++;
					break;
				} else if(sum < arr[i]) {
					left++;
				} else {
					right--;
				}
			}
		}
		System.out.println(cnt);
	}//end of main
}//end of class
