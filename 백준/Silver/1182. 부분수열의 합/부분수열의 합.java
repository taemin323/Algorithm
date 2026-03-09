import java.util.*;
import java.io.*;

public class Main {
	

	private static int N;
	private static int S;
	private static int[] arr;
	private static int answer = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		
		if(S == 0) answer--;
		
		System.out.println(answer);
	}// end of main

	private static void dfs(int depth, int sum) {
		if(depth == N) {
			if(sum == S) answer++;
			return;
		}
		
		dfs(depth + 1, sum + arr[depth]);// 선택.
		dfs(depth + 1, sum);// 선택 안함.
	}
}// end of class