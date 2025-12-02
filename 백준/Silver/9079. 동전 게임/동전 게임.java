import java.io.*;
import java.util.*;

public class Main {
	private static class Node {
		char[][] board;
		int dist;
		
		Node(char[][] b, int d) {
			this.board = b;
			this.dist = d;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			char[][] board = new char[3][3];
			
			for (int r = 0; r < 3; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < 3; c++) {
					board[r][c] = st.nextToken().charAt(0);
				}
			}
			
			System.out.println(bfs(board));
		}
	}// end of main	

	private static int bfs(char[][] board) {
		Queue<Node> q = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		
		q.add(new Node(board, 0));
		visited.add(toString(board));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(isGoal(cur.board)) return cur.dist;
			
			//8가지 뒤집는 연산 적용
			for (int i = 0; i < 8; i++) {
				char[][] next = copy(cur.board);
				flip(next, i);
				
				String key = toString(next);
				if(!visited.contains(key)) {
					visited.add(key);
					q.add(new Node(next, cur.dist+1));
				}
			}
		}
		return -1;
	}

	private static void flip(char[][] next, int type) {
		if(type < 3) {// row
			int r = type;
			for (int c = 0; c < 3; c++) {
				next[r][c] = flipOne(next[r][c]);
			}
		} else if(type < 6) {// col
			int c = type - 3;
			for (int r = 0; r < 3; r++) {
				next[r][c] = flipOne(next[r][c]);
			}
		} else if(type == 6) {// 우하향 대각선
			for (int i = 0; i < 3; i++) {
				next[i][i] = flipOne(next[i][i]);
			}
		} else {
			for (int i = 0; i < 3; i++) {// 우상향 대각선
				next[i][2-i] = flipOne(next[i][2-i]);
			}
		}
		
	}

	private static char flipOne(char c) {
		return (c == 'H') ? 'T' : 'H';
	}

	private static char[][] copy(char[][] board) {
		char[][] result = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result[i][j] = board[i][j];
			}
		}
		return result;
	}

	private static boolean isGoal(char[][] board) {
		boolean allH = true, allT = true;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(board[i][j] != 'H') allH = false;
				if(board[i][j] != 'T') allT = false;
			}
		}
		return allH || allT;
	}

	private static String toString(char[][] board) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(board[i][j]);
			}
		}
		return sb.toString();
	}
}// end of class
