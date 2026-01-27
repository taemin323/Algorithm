import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String A = br.readLine();
		String B = br.readLine();
		String result = br.readLine();
		
		if(check(A, result) && check(B, result)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		
	}// end of main

	private static boolean check(String str, String target) {
		int n = str.length();
		int m = target.length();
		
		for (int i = 0; i <= n - m; i++) {
			if(str.charAt(i) == target.charAt(0)) {
				boolean flag = true;
				
				for (int j = 0; j < m; j++) {
					if(str.charAt(j+i) != target.charAt(j)) {
						flag = false;
						break;
					}
					
				}
				if(flag) return true;
			}
		}
		return false;
	}
}// end of class