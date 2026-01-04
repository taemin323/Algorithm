import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = 0;
		int sum = 0;
		int answer = 0;
		
		while (true) {
			if(sum >= M) {
				sum -= arr[left++];
			} else if (right == N) {
				break;
			} else {
				sum += arr[right++];
			}
			
			if(sum == M) {
				answer++;
			}
		}
		System.out.println(answer);
	}// end of main
}// end of class