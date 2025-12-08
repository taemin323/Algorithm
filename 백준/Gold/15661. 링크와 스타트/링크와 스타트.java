import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	private static int N;
	private static int min = Integer.MAX_VALUE;
	private static int[] row;
	private static int[] column;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[][] w = new int[N][N];
        row = new int[N];
        column = new int[N];
        int totalSum = 0;
        for (int i = 0; i < N; i++) {
        	StringTokenizer stn = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		w[i][j] = Integer.parseInt(stn.nextToken());
        		totalSum += w[i][j];
        	}
        }

        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		row[i] += w[i][j];
        		column[i] += w[j][i];
        	}
        }

        dfs(0, 0, totalSum);

        System.out.println(min);
    }

    private static void dfs(int depth, int idx, int cur) {
    	if (depth > N - 1) {
    		return;
    	}
    	
    	// 언제 종료할 것인가?
    	min = Math.min(min, Math.abs(cur));

    	if (idx > N - 1) {
    		return;
    	}

    	dfs(depth + 1, idx + 1, cur - row[idx] - column[idx]);
    	dfs(depth, idx + 1, cur);
    }
}