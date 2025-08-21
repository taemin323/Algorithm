import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  1 <= X <= N < 10^K
 *  1 <= K <= 6
 *  1 <= P <= 42
 */
public class Main {
	private static int[][] display = {
			{1,1,1,0,1,1,1},// 0
			{0,0,1,0,0,0,1},//1
			{0,1,1,1,1,1,0},//2
			{0,1,1,1,0,1,1},//3
			{1,0,1,1,0,0,1},//4
			{1,1,0,1,0,1,1},//5
			{1,1,0,1,1,1,1},//6
			{0,1,1,0,0,0,1},//7
			{1,1,1,1,1,1,1},//8
			{1,1,1,1,0,1,1},//9
	};
	private static int cnt = 0;
	private static int N, K, P, X;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());// 총 N층
		K = Integer.parseInt(st.nextToken());// 디스플레이에 보이는 자릿수
		P = Integer.parseInt(st.nextToken());// 반전 시킬 LED 최대 개수
		X = Integer.parseInt(st.nextToken());// 반전시킬 층수 = 현재 층
		
		int[] xDigit = toDigit(X);
		check(xDigit);
		System.out.println(cnt);
	}// end of main
	
	private static void check(int[] xDigit) {
		for (int i = 1; i <= N; i++) {
			if(i == X) continue;
			if(isReverse(i, xDigit)) cnt++;
		}
	}

	private static boolean isReverse(int target, int[] xDigit) {
		int[] targetDigit = toDigit(target);
		
		int different = 0;
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < 7; j++) {
				if(display[xDigit[i]][j] != display[targetDigit[i]][j]) {
					different++;
					if(different > P) return false;
				}
			}
		}
		return true;
	}

	private static int[] toDigit(int X) {
		int[] result = new int[K];
		for (int i = K-1; i >= 0; i--) {
			result[i] = X % 10;
			X /= 10;
		}
		return result;
	}
}// end of class
