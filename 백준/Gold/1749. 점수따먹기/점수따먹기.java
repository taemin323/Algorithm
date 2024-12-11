import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 1 < N < 200
		int M = Integer.parseInt(st.nextToken()); // 1 < M < 200

		int[][] arr = new int[N + 1][M + 1];
		int[][] newArr = new int[N + 1][M + 1];
		

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= M; c++) {
				newArr[r][c] = newArr[r-1][c] + newArr[r][c-1] - newArr[r-1][c-1] + Integer.parseInt(st.nextToken());
			}
		} // 입력 완료

		int maxPrefix = Integer.MIN_VALUE;

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				for (int i = r; i <= N; i++) {
					for (int j = c; j <= M; j++) {
						maxPrefix = Math.max(maxPrefix, newArr[i][j] - newArr[r-1][j] - newArr[i][c-1] + newArr[r-1][c-1]);
					}
				}
			}
		}

		System.out.println(maxPrefix);
	}// end of main

}// end of clas
