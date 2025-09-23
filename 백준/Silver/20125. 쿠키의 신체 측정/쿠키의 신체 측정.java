import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	 
	static class Point {
		int r, c;
		
		public Point() {
		}
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[N+1][N+1];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = line.charAt(j);
				
				if(c == '_') board[i+1][j+1] = 0;
				else board[i+1][j+1] = 1;
			}
		}
		
		boolean flag = false;
		Point heart = new Point(-1,-1);
		int leftArm = 0;
		int rightArm = 0;
		int back = 0;
		int leftLeg = 0;
		int rightLeg = 0;
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				if(board[i][j] == 1 && !flag) {
					flag = true;
					heart.r = i+1;
					heart.c = j;
					sb.append(heart.r).append(" ").append(heart.c).append("\n");
				} 
				
				if(i == heart.r && board[i][j] == 1) {
					if(j < heart.c) {
						leftArm++;
					} else if(j > heart.c) {
						rightArm++;
					}
				}
				
				if(i > heart.r && board[i][j] == 1) {
					if(j == heart.c) {
						back++;
					} else if(j == heart.c - 1) {
						leftLeg++;
					} else if(j == heart.c + 1) {
						rightLeg++;
					}
				}
			}
		}
		sb.append(leftArm).append(" ").append(rightArm).append(" ").append(back).append(" ").append(leftLeg).append(" ").append(rightLeg).append(" ");
		System.out.println(sb.toString());
	}// end of main
}// end of class
