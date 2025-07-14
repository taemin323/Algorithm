import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int K;
	private static int[] strength;
	private static boolean[] robot;
	private static int step = 0;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		strength = new int[2*N];
		robot = new boolean[2*N];
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < strength.length; i++) {
			strength[i] = Integer.parseInt(st.nextToken());
		}//입력 완료
		
		while(true) {
			step++;
			rotate();
			move();
			put();
			
			if(count() >= K) {
				break;
			}
		}
		System.out.println(step);
		
	}// end of main

	private static int count() {
		int cnt = 0;
		for (int s: strength) {
			if (s == 0) {
				cnt++;
			}
		}
		return cnt;
	}

	private static void put() {
		if(!robot[0] && strength[0] > 0) {
			robot[0] = true;
			strength[0]--;
		}
	}

	private static void move() {
		for (int i = N-2; i >= 0; i--) {
			if(robot[i] && !robot[i+1] && strength[i+1] > 0) {
				robot[i] = false;
				robot[i+1] = true;
				strength[i+1]--;
				
				if (i+1 == N-1) {
					robot[i+1] = false;// 내리는 위치에 도달 
				}
			}
		}
	}
	
	// 벨트 + 로봇 회전
	private static void rotate() {
		int last = strength[2*N-1];
		for (int i = 2*N-1; i >= 1; i--) {
			strength[i] = strength[i-1];
		}
		strength[0] = last;
		
		// 로봇 회전
		for (int i = N-1; i >= 1; i--) {
			robot[i] = robot[i-1];
		}
		robot[0] = false;// 올리는 위치엔 로봇 없음
		robot[N-1] = false;// 내리는 위치엔 무조건 내려감
	}
}// end of class
