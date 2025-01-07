import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static long N;
	private static long K;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Long.parseLong(st.nextToken());
		K = Long.parseLong(st.nextToken());
		
		//K = (가로+1)*(세로+1), 가로+세로 = N

		if(bs(K)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		
	}//end of main

	private static boolean bs(long K) {
		long left = 0;
		long right = N/2;
		
		while(left <= right) {
			long row = (left+right)/2;
			long col = N - row;
			long sum = (row+1)*(col+1);
			
			if(sum == K) {
				return true;
			} else if(sum < K){
				left = row + 1;
			} else {
				right = row -1;
			}
		}
		return false;
	}
}//end of class