import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int HA;
	private static int t;
	private static int a;
	private static int h;
	private static int[][] rooms;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		HA = Integer.parseInt(st.nextToken());
		rooms = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			rooms[i][0] = Integer.parseInt(st.nextToken());
			rooms[i][1] = Integer.parseInt(st.nextToken());
			rooms[i][2] = Integer.parseInt(st.nextToken());
		}
		
		long left = 1;
		long right = 1_000_000_000_000_000_000L;// 최대 가능한 대미지 합산(10^18)
		long answer = right;
		
		while(left <= right) {
			long mid = left + (right - left) / 2;
			
			if(isClear(mid)) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(answer);
	}// end of main

	private static boolean isClear(long maxHp) {
		long curHp = maxHp;
		long curA = HA;
		
		for (int i = 0; i < N; i++) {
			int t = rooms[i][0]; 
			int a = rooms[i][1]; 
			int h = rooms[i][2];
			
			if(t == 1) {
				long attackCnt = h / curA;
				if(h % curA == 0) attackCnt--;
				
				long totalDamage = attackCnt * a;
				
				curHp -= totalDamage;
				if(curHp <= 0) return false;
			} else {
				curA += a;
				curHp = Math.min(maxHp, curHp + h);
			}
		}
		return true;
	}

}// end of class
