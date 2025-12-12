import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		
		boolean[] broken = new boolean[10];
		
		if(M > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				broken[Integer.parseInt(st.nextToken())] = true;
			}
		}
		// 현재 채널이 100부터 시작.
		int result = Math.abs(N-100);
		
		for (int i = 0; i <= 999999; i++) {
			String num = String.valueOf(i);
			
			boolean isBroken = false;
			for (int j = 0; j < num.length(); j++) {
				if(broken[num.charAt(j) - '0']) {
					//고장난 버튼을 눌러야 하면 멈춘다.
					isBroken = true;
					break;
				}
			}
			
			if(!isBroken) {
				//버튼을 누르는 횟수 중 가장 적은 횟수를 result에 담아.
				int min = Math.abs(N-i) + num.length();
				result = Math.min(min, result);
			}
		}
		System.out.println(result);
	}// end of main

	
}// end of class