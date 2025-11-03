import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] arr;
	private static int[] operators;
	private static int max;
	private static int min;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		operators = new int[4];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// +, -, x, %의 개수 (총 개수 : N-1)
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		Perm(1, arr[0]);
		
		System.out.println(max);
		System.out.println(min);
	}

	private static void Perm(int idx, int curResult) {
		
		if(idx == N) {
			max = Math.max(max, curResult);
			min = Math.min(min, curResult);
			return;
		}
		
		//덧셈
		if(operators[0] > 0) {
			operators[0]--;
			Perm(idx + 1, curResult + arr[idx]);
			operators[0]++;//백트래킹
		}
		
		//뺄셈
		if(operators[1] > 0) {
			operators[1]--;
			Perm(idx + 1, curResult - arr[idx]);
			operators[1]++;
		}
		
		//곱셈
		if(operators[2] > 0) {
			operators[2]--;
			Perm(idx + 1, curResult * arr[idx]);
			operators[2]++;
		}

		//나눗셈
		if(operators[3] > 0) {
			operators[3]--;
			Perm(idx + 1, curResult / arr[idx]);
			operators[3]++;
		}
	}
}