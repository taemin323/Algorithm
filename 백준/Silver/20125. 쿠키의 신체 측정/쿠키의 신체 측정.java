import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[N+1][N+1];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = line.charAt(j);
				
				if(c == '*') board[i+1][j+1] = 1;
			}
		}
		
		// 심장 찾기
		int heartR = -1, heartC = -1;
		outer:
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(board[i][j] == 1) {
					heartR = i + 1;
					heartC = j;
					break outer;
				}
			}
		}
		sb.append(heartR).append(" ").append(heartC).append("\n");
		
		//팔
		int leftArm = 0, rightArm = 0;
		for (int c = heartC-1; c >= 1 && board[heartR][c] == 1; c--) leftArm++;
		for (int c = heartC+1; c <= N && board[heartR][c] == 1; c++) rightArm++;
		
		// 허리
		int back = 0, backEndR = heartR;
		for (int r = heartR+1; r <= N && board[r][heartC] == 1; r++) {
			back++;
			backEndR = r;
		}
		
		// 다리
		int leftLeg = 0, rightLeg = 0;
		for (int r = backEndR + 1; r <= N && heartC - 1 >= 1 && board[r][heartC-1] == 1; r++) leftLeg++;
		for (int r = backEndR + 1; r <= N && heartC + 1 <= N && board[r][heartC+1] == 1; r++) rightLeg++;
		
		
		
		sb.append(leftArm).append(" ").append(rightArm).append(" ").append(back)
		.append(" ").append(leftLeg).append(" ").append(rightLeg);
		System.out.println(sb.toString());
	}// end of main
}// end of class
