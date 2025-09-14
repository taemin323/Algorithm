import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int C;
	private static int[] house;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		house = new int[N];
		for (int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		
		System.out.println(binarySearch());
		
	}// end of main

	private static int binarySearch() {
		int left = 1;
		int right = house[N - 1] - house[0];
		int ans = 0;
		
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(canInstall(mid)) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid -1 ;
			}
		}
		return ans;
	}

	private static boolean canInstall(int mid) {
		int cnt = 1;
		int last = house[0];
		
		for (int i = 0; i < N; i++) {
			if (house[i] - last >= mid) {
				cnt++;
				last = house[i];
				if(cnt >= C) return true;
			}
		}
		return false;
	}
}// end of class
