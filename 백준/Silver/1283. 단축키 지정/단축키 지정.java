import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		
		Set<Character> set = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			int markIdx = -1; //단축키로 지정할 인덱스
			
			
			//1단계 각 단어의 첫 글자 탐색
			for (int j = 0; j < line.length(); j++) {
				if ( j == 0 || line.charAt(j-1) == ' ') {
					char c = Character.toLowerCase(line.charAt(j));
					if (!set.contains(c)) {
						markIdx = j;
						break;
					}
				}
			}
			
			
			//2단계 전체 탐색
			if (markIdx == -1) {
				for (int j = 0; j < line.length(); j++) {
					char ch = line.charAt(j);
					if (ch == ' ') continue;
					char c = Character.toLowerCase(ch);
					if(!set.contains(c)) {
						markIdx = j;
						break;
					}
				}
			}
			
			//결과 출력 및 단축키 등록
			if(markIdx != -1) {
				set.add(Character.toLowerCase(line.charAt(markIdx)));
				System.out.println(change(line, markIdx));
			} else {
				System.out.println(line);
			}
		}
		
		
	}// end of main

	private static String change(String word, int idx) {
		return word.substring(0, idx) + "[" + word.charAt(idx) + "]" + word.substring(idx+1);
	}
}// end of class
