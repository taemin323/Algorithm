import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int[] arrA;
	private static int[] arrB;
	private static int[] arrC;
	private static int[] arrD;
	private static int[] sumAB;
	private static int[] sumCD;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		arrA = new int[N];
		arrB = new int[N];
		arrC = new int[N];
		arrD = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arrA[i] = Integer.parseInt(st.nextToken());
			arrB[i] = Integer.parseInt(st.nextToken());
			arrC[i] = Integer.parseInt(st.nextToken());
			arrD[i] = Integer.parseInt(st.nextToken());
		}
		
		// C[c] + D[d] = -(A[a] + B[b])
		
		// A와 B로 만들 수 있는 모든 합을 저장할 새로운 배열
		sumAB = new int[N*N];
		int idx = N*N - 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sumAB[idx--] = arrA[i] + arrB[j];
			}
		}
			
		// C와 D로 만들 수 있는 모든 합을 저장할 새로운 배열
		sumCD = new int[N*N];
		idx = N*N - 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sumCD[idx--] = arrC[i] + arrD[j];
			}
		}
		Arrays.sort(sumCD);
		
		
		long answer = 0;
		for (int i = 0; i < N*N; i++) {
			int target = -sumAB[i];

			int low = lowerBound(sumCD, target);
			int up = upperBound(sumCD, target);
			
			//같은 합이 여러 개 존재할 수 있음. 똑같은 값이 몇개 있는지도 알아내야함.
			// low = target이 처음 나타난 위치, up = target보다 큰 값이 처음 나타나는 위치
			// 개수 = up - low
			answer += (up - low);
		}
		System.out.println(answer);
	}// end of main

	private static int upperBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length;
		
		while(left < right) {
			int mid = left + (right - left) / 2;
			
			if(arr[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

	private static int lowerBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length;
		
		while(left < right) {
			int mid = left + (right - left) / 2;
			
			if(arr[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}// end of class