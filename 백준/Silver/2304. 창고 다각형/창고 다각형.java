import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 1 <= N <= 1000
 * 각 기둥의 왼쪽 면 위치 : L / 높이를 나타내는 H (모두 정수) 1 <= L,H <= 1000
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[1001];
		
		int left = Integer.MAX_VALUE;
		int right = Integer.MIN_VALUE;
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			
			arr[L] = H;
			
			left = Math.min(left, L);
			right = Math.max(right, L);
		}
		
		int max = 0;
		int maxL = 0;
		for (int i = left; i <= right; i++) {
			if(max < arr[i]) {
				max = arr[i];
				maxL = i;
			}
		}
		
		int leftMax = arr[left];
		int rightMax = arr[right];
		int leftL = left;
		int rightL = right;
		
		int sum = 0;
		while(left < maxL || right > maxL) {
			if(left < maxL) {
				left++;
				if(arr[left] >= leftMax) {
					sum += (left - leftL) * leftMax;
					leftMax = arr[left];
					leftL = left;
				}
			}
			
			if(right > maxL) {
				right--;
				if(arr[right] >= rightMax) {
					sum += (rightL - right) * rightMax;
					rightMax = arr[right];
					rightL = right;
				}
			}
		}
		
		sum += arr[maxL];
		
		System.out.println(sum);
		
	}// end of main
}//end of class
