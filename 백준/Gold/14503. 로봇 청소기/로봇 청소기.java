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
	private static int answer = 0;
	
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
		
		move(robotR, robotC, robotD);
			
		System.out.println(answer);
	}

	private static void move(int curR, int curC, int curD) {
		
		if(map[curR][curC] == 0) {
			 map[curR][curC] = 2;// 청소 처리
			 answer++;
		}
		
		boolean flag = false;
		int nd = curD;
		for(int k = 0; k < 4; k++) {
			nd = (nd + 3) % 4;
			int nr = curR + dr[nd];
			int nc = curC + dc[nd];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			
			if(map[nr][nc] == 0) {
				move(nr, nc, nd);
				flag = true;
				break;
			}
			
		}
		
		// 주변에 청소되지 않은 빈 칸이 없는 경우
		if(!flag) {
			//후진 처리
			int nextD = (curD+2) % 4;
			int nextR = curR + dr[nextD];
			int nextC = curC + dc[nextD];
			
			if(map[nextR][nextC] == 1) return;
			move(nextR, nextC, curD);
		}
		
	
	}
}
