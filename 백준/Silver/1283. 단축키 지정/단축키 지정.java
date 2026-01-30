import java.util.*;
import java.io.*;

public class Main {
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		int N = Integer.parseInt(br.readLine());
		Set<Character> set = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int markIdx = -1;
			
			// 1단계
			for (int j = 0; j < str.length(); j++) {
				if(j == 0 || str.charAt(j-1) == ' ') {
					char c = Character.toLowerCase(str.charAt(j));
					if(!set.contains(c) ) {
						markIdx = j;
						break;
					}
				}
			}
			
			// 2단계
			if(markIdx == -1) {
				for (int j = 0; j < str.length(); j++) {
					char c = str.charAt(j);
					if(c == ' ') continue;
					
					if(!set.contains(Character.toLowerCase(c))) {
						markIdx = j;
						break;
					}
				}
			}
			
			if(markIdx != -1) {
				set.add(Character.toLowerCase(str.charAt(markIdx)));
				System.out.println(change(str, markIdx));
			} else {
				System.out.println(str);
			}
		}
	
		
	}// end of main

	private static String change(String str, int idx) {
		return str.substring(0, idx) + "[" + str.charAt(idx) + "]" + str.substring(idx+1);
	}
}// end of class