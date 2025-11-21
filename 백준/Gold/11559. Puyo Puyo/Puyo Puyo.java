import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Main{
	private static int answer = 0;
	private static char[][] board;
	private static boolean[][] visited;
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};
	private static ArrayList<int[]> list;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		board = new char[12][6];
		for (int i = 0; i < 12; i++) {
			String str = br.readLine();
			for (int j = 0; j < 6; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		while(true) {
			visited = new boolean[12][6];
			list = new ArrayList<int[]>();// 이번 턴에 연쇄로 터트릴 명단
			
			// 탐색: 위 -> 아래, 왼쪽 -> 오른쪽
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if(board[i][j] != '.' && !visited[i][j]) {
						List<int[]> group = bfs(i, j, board[i][j]);
						if(group.size() >= 4) {
							list.addAll(group);
						}
					}
				}
			}
			
			if(list.isEmpty()) break;// 이번 턴에 연쇄할 게 없다면 break
			
			// 이번 턴 뿌요 다 터트리기
			for(int[] l : list) {
				board[l[0]][l[1]] = '.';
			}
			
			// 중력 적용
			gravity();
			
			answer++;
		}
		
		System.out.println(answer);
	}

	private static void gravity() {
		for (int c = 0; c < 6; c++) {
			Queue<Character> q = new LinkedList<>();
			
			// 위에서 아래로 스캔해서 뿌요만 스택에 담기
			for (int r = 11; r >= 0; r--) {
				if(board[r][c] != '.') {
					q.add(board[r][c]);
				}
			}
			
			// 아래에서부터 다시 채워넣기
			int r = 11;
			while(!q.isEmpty()) {
				board[r--][c] = q.poll();
			}
			
			// 남은 위쪽 칸은 . 으로 채우기
			while(r >= 0) {
				board[r--][c] = '.';
			}
		}
	}

	private static List<int[]> bfs(int i, int j, char puyo) {
		List<int[]> group = new ArrayList<>();
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i, j});
		visited[i][j] = true;
		group.add(new int[] {i, j});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = curR + dr[d];
				int nc = curC + dc[d];
				
				if(nr < 0 || nr >= 12 || nc < 0 || nc >= 6) continue;
				if(visited[nr][nc]) continue;
				if(board[nr][nc] == puyo) {
					visited[nr][nc] = true;
					q.add(new int[] {nr, nc});
					group.add(new int[] {nr, nc});
				}
			}
		}
		
		return group;
	}
}
