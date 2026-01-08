import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int M;
	private static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		
		// 그룹의 합의 최댓값을 mid로 해서 이분탐색을 해야함.
		int left = 0;
		int right = 0;
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			left = Math.max(left, arr[i]);// 구슬의 최댓값보다는 커야함.
			right += arr[i];// 한 그룹에 모든 구슬이 들어갈 경우.
		}
		
		int answer = 0;
		
		while(left <= right) {
			int mid = left + (right - left) / 2;
			
			if(canGroup(mid)) {// 그룹의 합이 mid 이하인가
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		int sum = 0;
		int cnt = 0;
		int groupCnt = 0;
		
		for (int i = 0; i < N; i++) {
			sum += arr[i];
			cnt++;
			
			// 남은 구슬의 개수 N-1-i
			// 앞으로 더 만들어야 할 그룹의 개수 M-1-groupCnt
			// -1을 해주는건 현재 처리하고 있는 구슬과 그룹을 제외한, 순수한 남은 것들만 비교하려고
			if(sum > answer || (N-1-i) < (M-1-groupCnt)) {
				// 방금 넣은 arr[i]는 다음 그룹으로 들어가야함
				sum -= arr[i];
				cnt--;
				
				sb.append(cnt).append(" ");
				groupCnt++;
				
				// 방금 뺀 arr[i]부터 새로운 그룹 시작
				sum = arr[i];
				cnt = 1;
			}
		}
		
		// 마지막 남은 그룹 출력
		sb.append(cnt).append(" ");
		System.out.println(answer);
		System.out.println(sb.toString());
	}// end of main	

	private static boolean canGroup(int mid) {
		int sum = 0;
		int cnt = 1;
		
		for (int i = 0; i < N; i++) {
			if(sum + arr[i] > mid) {//이번 구슬을 넣었을 때 mid를 넘으면
				cnt++;// 새 그룹 생성
				sum = arr[i];// 현재 구슬부터 새 그룹 시작
			} else {
				sum += arr[i];
			}
		}
		
		// 필요한 그룹 수가 M개 이하라면 가능
		return cnt <= M;
	}
}// end of class