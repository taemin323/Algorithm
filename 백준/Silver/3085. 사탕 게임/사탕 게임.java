import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static char[][] board;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(j + 1 < N) {
					swap(i,j,i,j+1);
					answer = Math.max(answer, check());
					swap(i,j,i,j+1);
				}
				
				if(i + 1 < N) {
					swap(i,j,i+1,j);
					answer = Math.max(answer, check());
					swap(i,j,i+1,j);
				}
			}
		}
		
		System.out.println(answer);
	}// end of main

	private static int check() {
		int maxCnt = 0;
		
		for (int i = 0; i < N; i++) {
			int cnt = 1;
			for (int j = 1; j < N; j++) {
				if(board[i][j] == board[i][j-1]) {
					cnt++;
				} else {
					maxCnt = Math.max(maxCnt, cnt);
					cnt = 1;
				}
			}
			maxCnt = Math.max(maxCnt, cnt);
		}

		for (int j = 0; j < N; j++) {
			int cnt = 1;
			for (int i = 1; i < N; i++) {
				if(board[i][j] == board[i-1][j]) {
					cnt++;
				} else {
					maxCnt = Math.max(maxCnt, cnt);
					cnt = 1;
				}
			}
			maxCnt = Math.max(maxCnt, cnt);
		}
		
		return maxCnt;
	}

	private static void swap(int i, int j, int i2, int j2) {
		char tmp = board[i][j];
		board[i][j] = board[i2][j2];
		board[i2][j2] = tmp;
	}

}// end of class
