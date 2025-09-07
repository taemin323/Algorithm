import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 1 <= N <= 500,000
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		char[] arr = new char[N];
		int rCnt = 0;
		int bCnt = 0;
		
		for (int i = 0; i < N; i++) {
			if(str.charAt(i) == 'R') {
				arr[i] = 'R';
				rCnt++;
			}
			else {
				arr[i] = 'B';
				bCnt++;
			}
		}

		// 왼쪽부터 연속
		int leftR = 0;
		int leftB = 0;
		
		for (int i = 0; i < N; i++) {
			if(arr[i] == 'R') {
				leftR++;
			}
			else break;
		}
		for (int i = 0; i < N; i++) {
			if(arr[i] == 'B') {
				leftB++;
			}
			else break;
		}
		
		// 오른쪽부터 연속
		int rightR = 0, rightB = 0;
		for (int i = N-1; i >= 0; i--) {
			if(arr[i] == 'R') {
				rightR++;
			}
			else break;
		}
		for (int i = N-1; i >= 0; i--) {
			if(arr[i] == 'B') {
				rightB++;
			}
			else break;
		}
		
		int ans = Math.min(Math.min(rCnt - leftR, bCnt - leftB), Math.min(rCnt - rightR, bCnt - rightB));
		
		System.out.println(ans);
	}// end of main
}// end of class
