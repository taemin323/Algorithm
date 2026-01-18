import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int[][] map;
	private static ArrayList<int[]> list;
	private static int[][] blocks;
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};
	private static int max;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());// 세로 길이
		M = Integer.parseInt(st.nextToken());// 가로 길이
		
		map = new int[N][M];
		
		// 값이 0인 좌표 저장 배열.
		list = new ArrayList<int[]>();
		
		// 뽑은 세 개의 벽 저장 배열.
		blocks = new int[3][2];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 0) list.add(new int[] {r, c});
			}
		}// 입력 완료
		max = Integer.MIN_VALUE;
		comb(list, 0, 0);
		System.out.println(max);
	}//end of main

	private static void comb(List<int[]> list, int idx, int sidx) {
		if(sidx == 3) {
			bfs(blocks);
			return;
		}
		
		for (int i = idx; i < list.size(); i++) {
			blocks[sidx][0] = list.get(i)[0];
			blocks[sidx][1] = list.get(i)[1];
			comb(list, i+1, sidx+1);
		}
	}

	private static void bfs(int[][] blocks) {
		int[][] temp = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		int cnt = 0;
		
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				temp[r][c] = map[r][c];
				if(map[r][c] == 2) {
					q.add(new int[] {r,c});
					visited[r][c] = true;
				}
			}
		}
		for (int[] i : blocks) {
			temp[i[0]][i[1]] = 1;
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for (int d = 0; d < dc.length; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && temp[nr][nc] == 0 && !visited[nr][nc]) {
					q.add(new int[] {nr, nc});
					visited[nr][nc] = true;
					temp[nr][nc] = 2;
				}
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(temp[r][c] == 0) {
					cnt++;
				}
			}
		}
		
		max = Math.max(max, cnt);
				
	}

}//end of class
