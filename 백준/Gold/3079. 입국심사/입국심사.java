import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {	
	private static long N;
	private static long M;
	private static long[] times;
	private static long ans = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());//입국 심사대 개수
		M = Integer.parseInt(st.nextToken());// 총 친구 수
		
		times = new long[(int)N];// 각 심사대 소요 시간 저장 배열
		
		for (int i = 0; i < N; i++) {
			times[i] = Integer.parseInt(br.readLine());
		}// 입력 완료
		
		Arrays.sort(times);
		
		bs();
		System.out.println(ans);
		
	}//end of main

	private static void bs() {
		long left = 0;
		long right = times[(int)N-1]*M;
		
		while(left <= right) {
			long mid = (left+right)/2;
			long total = 0;
			for (long time : times) {
				total += mid / time;
				if(total >= M) break;
			}
			
			if(total >= M) {
				right = mid - 1;
				ans = Math.min(ans, mid);
			} else {
				left = mid + 1;
			}
		}
	}
}//end of class
