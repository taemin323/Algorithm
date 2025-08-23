import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1 <= N <= 50, 1 <= L <=R <= 100
 * 0 <= 인구수 <= 100
 * 인구 이동이 발생하는 일수가 2,000번 보다 작ㅈ거나 같은 입력만 주어짐.
 */
public class Main {
	private static int N;
	private static int L;
	private static int R;
	private static int[][] map;
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};
	private static boolean[][] visited;
	private static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력 완료
		
		result = 0;
		while(true) {
			visited = new boolean[N][N];
			boolean moved = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						List<int[]> union = bfs(i,j);
						if(union.size() > 1) {
							moved = true;
							move(union);
						}
					}
				}
			}
			
			if(!moved) break;
			result++;
		}
		
		System.out.println(result);
	}// end of main

	private static List<int[]> bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		List<int[]> union = new ArrayList<>();
		
		q.offer(new int[] {i,j});
		visited[i][j] = true;
		union.add(new int[] {i,j});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			for (int d = 0; d < dc.length; d++) {
				int nr = curR + dr[d];
				int nc = curC + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if(visited[nr][nc]) continue;
				
				int diff = Math.abs(map[curR][curC] - map[nr][nc]);
				if(diff >= L && diff <= R) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr,nc});
					union.add(new int[] {nr,nc});
				}
			}
		}
		
		return union;
	}


	private static void move(List<int[]> union) {
		int total = 0;
		for (int[] u : union) {
			total += map[u[0]][u[1]];
		}
		
		int avg = total / union.size();
		
		for (int[] u : union) {
			map[u[0]][u[1]] = avg;
		}
		
	}

	
}//end of class
