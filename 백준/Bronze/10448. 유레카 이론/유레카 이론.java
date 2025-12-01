import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int[] list = new int[45];
		for (int i = 1; i < 45; i++) {
			list[i] = i * (i+1) / 2;
		}
		for (int tc = 0; tc < T; tc++) {
			
			int K = Integer.parseInt(br.readLine());
			
			int result = eureka(K, list);
			System.out.println(result);
				
			
		}
	}// end of main

	private static int eureka(int k, int[] list) {
		for (int i = 1; i < 45; i++) {
			for (int j = 1; j < 45; j++) {
				for (int j2 = 1; j2 < 45; j2++) {
					int sum = list[i] + list[j] + list[j2];
					if (sum == k) {
						return 1;
					}
				}
			}
		}
		return 0;
	}
}// end of class
