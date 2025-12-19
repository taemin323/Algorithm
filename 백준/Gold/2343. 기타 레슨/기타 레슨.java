import java.util.*;
import java.io.*;

public class Main {
	
	private static int N;
	private static int M;
	private static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int left = 0;
		int right = 0;
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			left = Math.max(left, arr[i]);
			right += arr[i];
		}
		
		System.out.println(bs(left, right));
	}// end of main

	private static int bs(int left, int right) {
		while(left <= right) {
			int sum = 0;
			int mid = (left + right) / 2;
			int cnt = 1;
			
			for (int i = 0; i < N; i++) {
				sum += arr[i];
				if(sum > mid) {
					sum = arr[i];
					cnt++;
				}
			}
			
			if(cnt <= M) {
				right = mid-1;
			} else {
				left = mid+1;
			}
			
		}
		return left;
	}
}// end of class