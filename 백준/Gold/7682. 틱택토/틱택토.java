import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int xCnt;
	private static int oCnt;
	private static char[][] board;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String str = br.readLine();
			if(str.equals("end")) break;
			
			xCnt = 0;
			oCnt = 0;
			board = new char[3][3]; 
			for (int i = 0; i < str.length(); i++) {
				char s = str.charAt(i);
				if(s == 'X') xCnt++;
				else if(s == 'O') oCnt++;
				
				board[i/3][i%3] = s;
			}
			if(check()) {
				sb.append("valid").append("\n");
			} else {
				sb.append("invalid").append("\n");
			}
			
		}
		System.out.println(sb.toString());
	}//end of main

	private static boolean check() {
		if(!(xCnt == oCnt || xCnt == oCnt + 1)) return false;
		
		boolean xWin = bingo('X');
		boolean oWin = bingo('O');
		
		// 동시 승리 없음
		if(xWin && oWin) return false;
		
		// 승자에 따른 카운트 일치
		if(xWin) return xCnt == oCnt + 1;
		if(oWin) return xCnt == oCnt;
		
		// 승리자가 없다면 보드가 꽉 차야함.
		return xCnt + oCnt == 9;
	}

	private static boolean bingo(char s) {
		//가로 체크
		for (int r = 0; r < 3; r++) {
			int count = 0;
			for (int c = 0; c < 3; c++) {
				if(board[r][c] == s) count++;
			}
			
			if(count == 3) {
				return true;
			}
		}
		
		// 세로 체크
		for (int c = 0; c < 3; c++) {
			int count = 0;
			for (int r = 0; r < 3; r++) {
				if(board[r][c] == s) count++;
			}
			
			if(count == 3) {
				return true;
			}
		}
		
		// 대각 체크
		if(board[0][0] == s && board[1][1] == s && board[2][2] == s) return true;
		if(board[0][2] == s && board[1][1] == s && board[2][0] == s) return true;
		return false;
	}

}//end of class
