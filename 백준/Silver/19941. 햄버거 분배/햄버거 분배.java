import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static char[] arr;
	private static int ans = 0;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		arr = new char[N];
		for (int i = 0; i < N; i++) {
			arr[i] = str.charAt(i);
		}
		
		for (int i = 0; i < N; i++) {
			if(arr[i] == 'P') {
				boolean flag = false;
				
				//먹을 수 있는 햄버거 왼쪽 범위 탐색
				int left = Math.max(i - K, 0);
				for (int j = left; j < i; j++) {
					if(burgerCheck(j)) {
						flag = true;
						break;
					}
				}
				
				//오른쪽 탐색
				if(!flag) {
					int right = Math.min(i + K, N - 1);
					for (int j = i + 1; j <= right; j++) {
						if(burgerCheck(j)) break;
					}
				}
			}
		}
		
		System.out.println(ans);
	}//end of main

	private static boolean burgerCheck(int idx) {
		if (arr[idx] == 'H') {
			arr[idx] = 'X';// 햄버거를 먹었으니까 X로 변경해주자.
			ans++;
			return true;
		}
		return false;
	}
}//end of class
