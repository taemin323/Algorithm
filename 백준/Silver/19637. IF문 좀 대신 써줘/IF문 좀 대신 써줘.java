import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 1 <= N <= 100,000
 * 1 <= M <= 100,000
 * 1 <= 칭호의 길이 <= 11 (영어 대문자)
 * 전투력 상한값 <= 1,000,000,000
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[] names = new String[N];
		int[] powers = new int[N];
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			names[i] = st.nextToken();
			powers[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int x = Integer.parseInt(br.readLine());
			
			//이진 탐색
			int idx = binarySearch(powers, x);
			sb.append(names[idx]).append("\n");
		}
		
		System.out.println(sb.toString());
	}// end of main
	
	private static int binarySearch(int[] powers, int target) {
		int left = 0, right = powers.length - 1;
		int result = powers.length;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(target <= powers[mid]) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return result;
	}

}// end of class
