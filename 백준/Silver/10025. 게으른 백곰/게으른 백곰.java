import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[1000001];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			arr[x] = g;
		}
		
		long sum = 0;
		long answer = 0;
		
		int windowSize = 2 * K + 1;
		
		for (int i = 0; i <= 1000000; i++) {
			sum += arr[i];
			
			if(i >= windowSize) {
				sum -= arr[i- windowSize];
			}
			
			answer = Math.max(answer, sum);
		}
		System.out.println(answer);
	}// end of main
}// end of class