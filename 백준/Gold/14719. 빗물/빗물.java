import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] blocks = new int[W];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < blocks.length; i++) {
			blocks[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		//왼쪽 최대 높이
		int[] leftMax = new int[W];
		leftMax[0] = blocks[0];
		for (int i = 1; i < W; i++) {
			leftMax[i] = Math.max(leftMax[i-1], blocks[i]);
		}
		
		//오른쪽 최대 높이
		int[] rightMax = new int[W];
		rightMax[W-1] = blocks[W-1];
		for (int i = W - 2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i+1], blocks[i]);
		}
		
		// 각 칸에서 빗물 계산
		int cnt = 0;
		for (int i = 1; i < W - 1; i++) {
			int water = Math.min(leftMax[i], rightMax[i]) - blocks[i];
			if (water > 0) {
				cnt += water;
			}
		}
		
		System.out.println(cnt);
	}// end of main
}// end of class
