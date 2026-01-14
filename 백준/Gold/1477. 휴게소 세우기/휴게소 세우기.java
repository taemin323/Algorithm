import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int M;
	private static int L;
	private static int[] location;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		location = new int[N+2];
		location[0] = 0;
		location[1] = L;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 2; i < N+2; i++) {
			location[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(location);
		
		int left = 1;
		int right = L-1;
		int answer = 0;
		
		while(left <= right) {
			int mid = left + (right - left) / 2;
			
			if(calculate(mid) <= M) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(answer);
	}// end of main

	private static int calculate(int mid) {
		int cnt = 0;

		for (int i = 1; i < N+2; i++) {
			int prev = location[i-1];
			int cur = location[i];
			int len = cur - prev;
			
			cnt += (len-1) / mid;
		}
		return cnt;
	}
}// end of class