import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		int max = 0;
		int left = 0;
		int oddCnt = 0;//제거한 홀수 개수
		
		//투포인터
		for (int right = 0; right < N; right++) {
			if(arr[right] % 2 != 0) {
				oddCnt++;
			}
			
			while(oddCnt > K) {
				if(arr[left] % 2 != 0) {
					oddCnt--;
				}
				left++;
			}
			max = Math.max(max, right-left+1-oddCnt);
		}
		
		System.out.println(max);
	
	}//end of main
}//end of class
