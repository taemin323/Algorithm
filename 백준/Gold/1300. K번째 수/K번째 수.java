import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		long left = 1;
		long right = k;
		long answer = 0;
		
		while(left <= right) {
			long mid = left + (right - left) / 2;
			long cnt = 0;
			
			for(int i = 1; i <= N; i++) {
				cnt += Math.min(mid/i, N);
			}
		
			if(cnt >= k) {
				answer = mid;
				right = mid-1;
			} else {
				left = mid+1;
			}
		}
		System.out.println(answer);
	}// end of main
}// end of class