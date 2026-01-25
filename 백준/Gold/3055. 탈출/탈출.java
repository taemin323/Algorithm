import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int R;
	private static int C;
	private static char[][] map;
	private static int rowS;
	private static int colS;
	private static int rowD;
	private static int colD;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static char[][] newMap;
	private static int[][] count;
	private static Queue<int[]> q = new LinkedList<>();
	private static Queue<int[]> water = new LinkedList<>();
	private static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());// R행 <= 50
		C = Integer.parseInt(st.nextToken());// C행 <= 50

		map = new char[R][C];
		count = new int[R][C];
		
		
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				if (map[r][c] == 'S') {
					q.add(new int[] {r,c,1});
				} else if (map[r][c] == '*') {
					water.add(new int[] {r,c});
				}
			}
		} // 입력 완료
		
		ans = Integer.MAX_VALUE;
		
		bfs();
		System.out.println(ans == Integer.MAX_VALUE? "KAKTUS":ans);
	
	}// end of main

	private static void bfs() {
	
		while(!q.isEmpty()) {
			int len = water.size();
			for (int i = 0; i < len; i++) {
				int[] w = water.poll();
				int r = w[0];
				int c = w[1];
				for (int d = 0; d < dc.length; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == '.') {
						map[nr][nc] = '*';
						water.add(new int[] {nr,nc});
					}
				}
			}
			
			len = q.size();
			for (int i = 0; i < len; i++) {
				int[] s = q.poll();
				int r = s[0];
				int c = s[1];
				int time = s[2];
				for (int d = 0; d < dc.length; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
						if(map[nr][nc] == 'D') {
							ans = Math.min(ans, time);
							return;
						} else if(map[nr][nc] == '.') {
							map[nr][nc] = 'S';
							q.add(new int[] {nr,nc,time+1});
						}
					}
					
				}
			}
		}
		
		
	}

}// end of class
