import java.util.*;
import java.io.*;

public class Main {
	private static long N;
	private static int M;
	private static int[] times;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Long.parseLong(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		times = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			times[i] = Integer.parseInt(st.nextToken());
		}
		
		// 아이들의 수가 놀이기구 수보다 적다면
		if(N <= M) {
			System.out.println(N);
			return;
		}
		
		// 시간을 이분탐색 대상으로.
		long left = 0;
		long right = N*30L;
		long targetTime = 0;
		
		while(left <= right) {
			long mid = left + (right - left) / 2;
			
			// mid시간까지 총 몇 명이 탈 수 있는지 계산
			if(calculate(mid) >= N) {
				targetTime = mid;// N명 이상 탔으므로 시간 후보로 저장
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		//targetTime - 1분까지 탄 아이들의 수
		long prevTargetTime = calculate(targetTime-1);
		
		// targetTime 사이클때 새롭게 탑승하는 기구를 확인
		for (int i = 0; i < M; i++) {
			if(targetTime % times[i] == 0) {//targetTime에 비는 기구
				prevTargetTime++;
				if(prevTargetTime == N) {
					System.out.println(i+1);//기구가 1번부터 시작하므로.
					return;
				}
			}
		}
	}// end of main

	private static long calculate(long t) {

		long cnt = M;//0분에 일단 모든 기구에 한 명씩 탈 수 있음
		for (int i = 0; i < M; i++) {
			cnt += (t / times[i]);
		}
		return cnt;
	}
}// end of class