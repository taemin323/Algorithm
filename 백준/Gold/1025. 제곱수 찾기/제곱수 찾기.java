import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int M;
	private static int[][] board;
	private static int answer = -1;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        board = new int[N][M];
        
        for (int i = 0; i < N; i++) {
        	String str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j) - '0';
			}
		}
        
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				calculate(i,j);
			}
		}
        
        System.out.println(answer);
    }// end of main

	private static void calculate(int r, int c) {
		for (int dr = -N; dr < N; dr++) {
			for (int dc = -M; dc < M; dc++) {
				
				// 0,0은 이동이 없으므로 무조건 제외
				if(dr == 0 && dc ==0) continue;
				
				int curR = r;// 시작행
				int curC = c;// 시작열
				int sqr = 0;
				
				while(curR >= 0 && curR < N && curC >= 0 && curC <M) {
					sqr = 10 * sqr + board[curR][curC];
					
					int root = (int) Math.sqrt(sqr);
					if(Math.pow(root, 2) == sqr) {
						answer = Math.max(answer, sqr);
					}
					curR += dr;
					curC += dc;
				}
			}
		}
	}
}// end of class