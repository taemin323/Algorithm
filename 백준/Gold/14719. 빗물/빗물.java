import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
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
		
		int cnt = 0;
		
		for (int i = 1; i < blocks.length - 1; i++) {
			int leftMax = 0;
			int rightMax = 0;
			
			for (int l = 0; l < i; l++) {
				leftMax = Math.max(leftMax, blocks[l]);
			}
			
			for (int r = i + 1; r < blocks.length; r++) {
				rightMax = Math.max(rightMax, blocks[r]);
			}
			
			int curWater = Math.min(leftMax, rightMax) - blocks[i];
			if (curWater > 0) {
				cnt += curWater;
			}
		}
		System.out.println(cnt);
	}// end of main
}// end of class
