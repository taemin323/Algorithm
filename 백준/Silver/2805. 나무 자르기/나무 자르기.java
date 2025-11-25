import java.io.*;
import java.util.*;

public class Main {
	


	private static int N;
	private static int M;
	private static int maxH;
	private static int[] trees;
	private static int minH;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trees = new int[N];
		
		maxH = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			
			maxH = Math.max(maxH, trees[i]);
		}
		
		int left = 0;
		int right = maxH;
		int answer = 0;
		while(left <= right) {
			long sum = 0;
			int mid = (left + right) / 2;
			
			for (int i = 0; i < N; i++) {
				if(trees[i] > mid) {
					sum += trees[i] - mid;
				}
			}
			
			if(sum >= M) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid -1;
			}
			
		}
		System.out.println(answer);
	}// end of main
}// end of class
