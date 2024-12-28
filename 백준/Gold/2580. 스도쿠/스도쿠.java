import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		
	}
	
	private static int[][] board;
	private static ArrayList<Point> blanks;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		board = new int[9][9];
		blanks = new ArrayList<Point>();//빈 부분 좌표 저장.
		
		for (int r = 0; r < 9; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int c = 0; c < 9; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if(board[r][c] == 0) {
					blanks.add(new Point(r, c));
				}
			}
		}//입력완료
		
		sudoku(0);
		
	}//end of main

	private static void sudoku(int index) {
		//모든 빈칸을 채웠다면 출력.
		if(index == blanks.size()) {
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					sb.append(board[r][c]).append(" ");
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			System.exit(0);
		}
		
		Point cur = blanks.get(index);
		int r = cur.r;
		int c = cur.c;
		
		for (int i = 1; i <= 9; i++) {
			if(check(r,c, i)) {
				board[r][c] = i;
				sudoku(index+1);
				board[r][c] = 0;
			}
		}
		
	}

	private static boolean check(int r, int c, int number) {
		
		//가로 검사
		for (int i = 0; i < 9; i++) {
			if(board[r][i] == number) {
				return false;
			}
		}
		
		//세로 검사
		for (int i = 0; i < 9; i++) {
			if(board[i][c] == number) {
				return false;
			}
		}
		
		//3*3 구역 검사
		int startR = (r/3)*3;
		int startC = (c/3)*3;
		for (int i = startR; i < startR+3; i++) {
			for (int j = startC; j < startC+3; j++) {
				if(board[i][j] == number) {
					return false;
				}
			}
		}
		
		return true;
	}

}//end of class
