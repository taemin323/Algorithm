import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());// 전체 용액의 수 2<= N <=100,000
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//입력 완료
		
		Arrays.sort(arr);
		
		int left = 0;
		int right = arr.length - 1;
		
		int gap = Integer.MAX_VALUE;
		int result1 = 0;
		int result2 = 0;
		
		int tmp;
		int sum;
		
		while(left < right) {
			sum = arr[left] + arr[right];
			tmp = Math.abs(sum);
			
			if(tmp < gap) {
				gap = tmp;
				result1 = arr[left];
				result2 = arr[right];
			}
			
			if(sum > 0) {
				right--;
			} else {
				left++;
			}
		}
		
		System.out.println(result1+" "+result2);
	}// end of main
}// end of class
