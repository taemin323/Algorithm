import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] wheels;
	private static int K;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		wheels = new int[4][8];
		
		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheels[i][j] = str.charAt(j) - '0';
			}
		}
		
		K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int wheelNum = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());// 1 : 시계 방향, -1 : 반시계 방향
			
			//결국엔 중요한 위치는
			//wheels[0][2], wheels[1][2], wheels[1]6], wheels[2][2], wheels[2][6], wheels[3][6]
			gearOperation(wheelNum, dir);
		}
		
		int answer = 0;
		
		for (int i = 0; i < 4; i++) {
			answer += Math.pow(2, i) * wheels[i][0];
		}
		System.out.println(answer);
	}// end of main

	private static void rotate(int wheelNum, int dir) {
		if(dir == 1) {
			int tmp = wheels[wheelNum][7];
			for (int i = 7; i > 0; i--) {
				wheels[wheelNum][i] = wheels[wheelNum][i-1];
			}
			wheels[wheelNum][0] = tmp;
			
		} else {
			int tmp = wheels[wheelNum][0];
			for (int i = 0; i < 7; i++) {
				wheels[wheelNum][i] = wheels[wheelNum][i+1];
			}
			wheels[wheelNum][7] = tmp;
		}
	}
	
	private static void gearOperation(int wheelNum, int dir) {
		//왼쪽
		leftSide(wheelNum-1, -dir);
		//오른쪽
		rightSide(wheelNum+1, -dir);
		rotate(wheelNum, dir);
	}

	private static void rightSide(int wheelNum, int dir) {
		if(wheelNum > 3) return;
		
		if(wheels[wheelNum][6] == wheels[wheelNum-1][2]) return;
		
		rightSide(wheelNum+1, -dir);
		rotate(wheelNum, dir);
	}

	private static void leftSide(int wheelNum, int dir) {
		if(wheelNum < 0) return;
		
		if(wheels[wheelNum][2] == wheels[wheelNum+1][6]) return;
		
		leftSide(wheelNum-1, -dir);
		rotate(wheelNum, dir);
	}

}// end of class
