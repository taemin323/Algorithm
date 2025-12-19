import java.util.*;
import java.io.*;

public class Main {
	
	private static int N;
	private static int[] note1;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			StringBuilder sb = new StringBuilder();
			
			N = Integer.parseInt(br.readLine());
			
			note1 = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				note1[i] = Integer.parseInt(st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());
			
			int[] note2 = new int[M];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				note2[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(note1);
			
			for (int i = 0; i < M; i++) {
				sb.append(bs(note2[i])).append("\n");
			}
			System.out.print(sb.toString());
		}
		
	}// end of main

	private static int bs(int num) {
		int left = 0, right = N-1;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(note1[mid] == num) return 1;
			if(note1[mid] < num) left = mid + 1;
			else right = mid -1;
		}
		return 0;
	}
}// end of class