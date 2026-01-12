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
		Arrays.sort(sumAB);
			
		// C와 D로 만들 수 있는 모든 합을 저장할 새로운 배열
		sumCD = new int[N*N];
		idx = N*N - 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sumCD[idx--] = arrC[i] + arrD[j];
			}
		}
		Arrays.sort(sumCD);
		
		// 하나는 앞에서부터, 나머지 하나는 뒤에서부터
		// sumAB에 2가 3개있고, sumCD에 -2가 4개있다면 0이 되는건 총 3*4개
		int left = 0;
		int right = N*N - 1;
		long answer = 0;
		
		while(left < N*N && right >= 0) {
			int num1 = sumAB[left];
			int num2 = sumCD[right];
			int sum = num1 + num2;
			
			if(sum == 0) {
				long cnt1 = 0;
				long cnt2 = 0;
				
				// sumAB에서 현재값과 똑같은 숫자들의 개수를 카운트
				while(left < N*N && sumAB[left] == num1) {
					cnt1++;
					left++;
				}

				// sumCD에서 현재값과 똑같은 숫자들의 개수를 카운트
				while(right >= 0 && sumCD[right] == num2) {
					cnt2++;
					right--;
				}
				
				// 두 개수를 곱한 만큼 정답에 더한다
				answer += cnt1 * cnt2;
			} else if(sum < 0) {
				left++;
			} else {
				right--;
			}
		}
		System.out.println(answer);
	}// end of main

}// end of class