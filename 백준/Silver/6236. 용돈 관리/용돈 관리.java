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
		arr = new int[N];
		
		int left = 0;// 최소 k
		int right = 0;// 최대 k
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			left = Math.max(arr[i], left);
			right += arr[i];
		}
		
		int answer = right;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(isManage(mid)) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(answer);
	}// end of main

	private static boolean isManage(int K) {
		int cnt = 1;
		int money = K;
		
		for (int i = 0; i < N; i++) {
			if(money < arr[i]) {
				cnt++;
				money = K;
			}
			money -= arr[i];
		}
		return cnt <= M;
	}
}// end of class