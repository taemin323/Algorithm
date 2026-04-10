import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int N;
	private static int M;
	private static int[] dr = {-1,0,1,0};//북동남서
	private static int[] dc = {0,1,0,-1};//북동남서
	private static int[][] map;
	private static int robotR;
	private static int robotC;
	private static int robotD;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		robotR = Integer.parseInt(st.nextToken());
		robotC = Integer.parseInt(st.nextToken());
		robotD = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		
		while(true) {
			if(map[robotR][robotC] == 0) {
				map[robotR][robotC] = 2;
				answer++;
			}
			
			boolean flag = false;
			
			//주변 탐색
			for (int d = 0; d < 4; d++) {
				robotD = (robotD + 3) % 4;
				int nr = robotR + dr[robotD];
				int nc = robotC + dc[robotD];
				
				if(map[nr][nc] == 0) {
					robotR = nr;
					robotC = nc;
					flag = true;
					break;
				}
			}
			
			// 빈 칸 없는 경우
			if(!flag) {
				int backD = (robotD+2) % 4;
				int backR = robotR + dr[backD];
				int backC = robotC + dc[backD];
				
				if(map[backR][backC] == 1) break;
				robotR = backR;
				robotC = backC;
			}
		}
		
		System.out.println(answer);
	}

}
