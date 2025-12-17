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
		
		N = Integer.parseInt(br.readLine());
		cards = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(cards);
		
		M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			
			// 정렬 후 , x가 마지막으로 등장한 다음 인덱스 - x가 처음 등장한 인덱스를 해주면 x의 개수
			int cnt = upperBound(target) - lowerBound(target);
			sb.append(cnt).append(" ");
		}
		
		System.out.println(sb.toString());
	}// end of main

	private static int lowerBound(int target) {
		int left = 0, right = N;
		
		while(left < right) {
			int mid = (left+right) / 2;
			
			if(cards[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static int upperBound(int target) {
		int left = 0, right = N;
		
		while(left < right) {
			int mid = (left+right) / 2;
			
			if(cards[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}// end of class