import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[][] dp;
	private static int[] cards;
	private static int[] arr;
	private static int[][] answer;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		
		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) {
			int key = Integer.parseInt(st.nextToken());
			
			map.put(key, map.getOrDefault(key, 0)+1);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int key = Integer.parseInt(st.nextToken());
			
			sb.append(map.getOrDefault(key, 0)).append(" ");
		}
		System.out.println(sb.toString());
	}// end of main
}// end of class