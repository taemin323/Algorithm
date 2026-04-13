import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int N;
	private static int M;
	private static int[][] map;
	private static int curR;
	private static int curC;
	private static int curD;
	private static int[] dr = {-1,0,1,0};//북동남서
	private static int[] dc = {0,1,0,-1};
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		
		curR = Integer.parseInt(st.nextToken());
		curC = Integer.parseInt(st.nextToken());
		curD = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		
		while(true) {
			// 현재 칸 청소
			if(map[curR][curC] == 0) {
				map[curR][curC] = 2;
				answer++;
			}
			
			boolean flag = false;
			
			int nd = curD;
			//현재 칸의 주변 4칸 탐색
			for(int i = 0; i < 4; i++) {
				nd = (nd+3)%4;
				int nr = curR + dr[nd];
				int nc = curC + dc[nd];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if(map[nr][nc] == 0) {
					flag = true;
					curR = nr;
					curC = nc;
					curD = nd;
					break;
				}
			}
			
			// 주변에 청소되지 않은 빈 칸이 없는 경우
			if(!flag) {
				int backR = curR + dr[(curD+2)%4];
				int backC = curC + dc[(curD+2)%4];
				
				if(backR < 0 || backR >= N || backC < 0 || backC >= M) continue;
				
				if(map[backR][backC] == 1) break;
				
				curR = backR;
				curC = backC;
			}
		}
		
		System.out.println(answer);
	}
}
