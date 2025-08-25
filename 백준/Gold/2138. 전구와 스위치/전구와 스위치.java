import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 2 <= N <= 100,000
 * i번 스위치를 누르면 i-1, i, i+1의 세 개의 전구의 상태가 바뀐다.
 * 불가능한 경우 -1 출력
 */
public class Main {
	private static int N;
	private static int[] lightArr;
	private static int[] resultArr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		// lights -> result로 만들어야함.
		String light = br.readLine();
		String result = br.readLine();
		
		lightArr = new int[N];
		resultArr = new int[N];
		
		for (int i = 0; i < N; i++) {
			lightArr[i] = light.charAt(i) - '0';
			resultArr[i] = result.charAt(i) - '0';
		}
		
		
		// 첫 번째 스위치 안누름
		int cnt1 = calculate(lightArr.clone(), false);
		
		// 첫 번째 스위치 누름
		int cnt2 = calculate(lightArr.clone(), true);
		
		int ans = -1;
		if(cnt1 >= 0 && cnt2 >= 0) ans = Math.min(cnt1, cnt2);
		else if(cnt1 >= 0) ans = cnt1;
		else if(cnt2 >= 0) ans = cnt2;
		
		System.out.println(ans);
		
	}// end of main

	private static int calculate(int[] arr, boolean pressFirst) {
		int cnt = 0;
		
		if(pressFirst) {
			press(arr, 0);
			cnt++;
		}
		
		for (int i = 1; i < N; i++) {
			if(arr[i-1] != resultArr[i-1]) {
				press(arr, i);
				cnt++;
			}
		}
		
		for (int i = 0; i < N; i++) {
			if(arr[i] != resultArr[i]) return -1;
		}
		
		return cnt;
	}

	private static void press(int[] arr, int i) {
		for (int j = i-1; j <= i+1; j++) {
			if(j >= 0 && j < N) {
				arr[j] = arr[j] == 0 ? 1 : 0;
			}
		}
	}

	
}// end of class
