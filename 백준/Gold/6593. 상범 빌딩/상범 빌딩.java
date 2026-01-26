import java.util.*;
import java.io.*;

public class Main {
	private static int L;
	private static int R;
	private static int C;
	private static char[][][] map;
	private static boolean[][][] visited;
	private static int[] dl = {1,-1,0,0,0,0};//위 아래 북 남 서 동
	private static int[] dr = {0,0,1,-1,0,0};
	private static int[] dc = {0,0,0,0,1,-1};
	private static int answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new char[L][R][C];
			visited = new boolean[L][R][C];
			answer = 0;
			
			if(L == 0 && R == 0 && C == 0) break;
			
			int startL = 0;
			int startR = 0;
			int startC = 0;
			
			for (int l = 0; l < L; l++) {
				for (int r = 0; r < R; r++) {
					String str = br.readLine();
					for (int c = 0; c < C; c++) {
						map[l][r][c] = str.charAt(c);
						
						if(map[l][r][c] == 'S') {
							startL = l;
							startR = r;
							startC = c;
						}
					}
				}
				String tmp = br.readLine();//빈칸
			}
			
			bfs(startL, startR, startC);
			if(answer == 0) {
				sb.append("Trapped!").append("\n");
			} else {
				sb.append("Escaped in ").append(answer).append(" minute(s).").append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}// end of main

	private static void bfs(int l, int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {l,r,c, 0});
		visited[l][r][c] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curL = cur[0];
			int curR = cur[1];
			int curC = cur[2];
			int curTime = cur[3];
			
			if(map[curL][curR][curC] == 'E') {
				answer = curTime;
				return;
			}
			
			for (int d = 0; d < 6; d++) {
				int nl = curL + dl[d];
				int nr = curR + dr[d];
				int nc = curC + dc[d];
				
				if(nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				
				if(!visited[nl][nr][nc] && map[nl][nr][nc] != '#') {
					visited[nl][nr][nc] = true;
					q.add(new int[] {nl,nr,nc, curTime + 1});
				}
			}
		}
		
	}
}// end of class