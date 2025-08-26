import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int N;
	private static int[] results;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String light = br.readLine();
		String result = br.readLine();
		// 입력 완료
		
		int[] lights = new int[N];
		results = new int[N];
		for (int i = 0; i < N; i++) {
			lights[i] = light.charAt(i) - '0';
			results[i] = result.charAt(i) - '0';
		}

		//case1 첫 번째 전구 스위치 안눌렀을 때
		int cnt1 = doSwitch(lights.clone(), false);
		
		//case2 첫 번째 전구 스위치 눌렀을 때
		int cnt2 = doSwitch(lights.clone(), true);
		
		int ans = -1;
		if(cnt1 >= 0 && cnt2 >= 0) ans = Math.min(cnt1, cnt2);
		else if(cnt1 >= 0) ans = cnt1;
		else if(cnt2 >= 0) ans = cnt2;
		
		System.out.println(ans);
		
	}// end of main

	private static int doSwitch(int[] lights, boolean pressFirst) {
		int cnt = 0;
		
		if(pressFirst) {
			toggle(lights, 0);
			cnt++;
		}
		
		for (int i = 1; i < N; i++) {
			if(lights[i-1] != results[i-1]) {
				toggle(lights, i);
				cnt++;
			}
		}
		
		for (int i = 0; i < N; i++) {
			if(lights[i] != results[i]) {
				return -1;
			}
		}
		
		return cnt;
	}

	private static void toggle(int[] lights, int idx) {
		for (int i = idx-1; i <= idx+1; i++) {
			if(i >= 0 && i < N) {
				lights[i] = lights[i] == 0 ? 1 : 0;
			}
		}
	}
}// end of class
