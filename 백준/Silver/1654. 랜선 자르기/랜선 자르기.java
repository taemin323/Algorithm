import java.util.*;
import java.io.*;

public class Main {
	
	private static int K;
	private static int N;
	private static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[K];
		
		long left = 1;
		long right = 0;
		
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
			right = Math.max(right, arr[i]);
		}
		
		long answer = 0;
		
		while (left <= right) {
			long mid = left + (right - left) / 2;//오버플로우 방지
			
			long cnt = 0;
			for (int len : arr) {
				cnt += (len / mid);
			}
			
			if(cnt >= N) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(answer);
	}// end of main
}// end of class