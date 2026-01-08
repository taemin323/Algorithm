import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int M;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		long left = 1;
		long right = M;
		long answer = 0;
		
		while(left <= right) {
			long mid = left + (right - left) / 2;
			
			if(calculate(mid) >= M) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(answer);
	}// end of main

	private static long calculate(long mid) {
		long cnt = 0;
		
		for (int i = 1; i <= N; i++) {
			cnt += Math.min(mid/i, N);
		}
		return cnt;
	}
}// end of class