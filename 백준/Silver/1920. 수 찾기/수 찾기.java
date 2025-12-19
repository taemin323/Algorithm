import java.util.*;
import java.io.*;

public class Main {
	
	private static int N;
	private static int M;
	private static int[] arr1;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		arr1 = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		
		int[] arr2 = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr1);
		
		for (int i = 0; i < M; i++) {
			sb.append(bs(arr2[i])).append("\n");
		}
		System.out.println(sb.toString());
	}// end of main

	private static int bs(int num) {
		int left = 0, right = N-1;
		
		while(left <= right) {
			int mid = (left+right) / 2;
			
			if(arr1[mid] == num) return 1;
			if(arr1[mid] < num) left = mid+1;
			else right = mid-1;
		}
		
		return 0;
	}
}// end of class