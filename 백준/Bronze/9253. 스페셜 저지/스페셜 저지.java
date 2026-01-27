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
		
		if(kmp(A, result) && kmp(B, result)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}// end of main

	// KMP 매칭 함수
	private static boolean kmp(String text, String pattern) {
		int[] pi = getPi(pattern);
		int n = text.length();
		int m = pattern.length();
		int j = 0;
		
		for (int i = 0; i < n; i++) {
			// 현재 글자가 패턴과 다르면 pi 배열을 이용해 점프
			while(j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			
			if(text.charAt(i) == pattern.charAt(j)) {
				if(j == m-1) {
					return true;
				} else {
					j++;
				}
			}
		}
		return false;
	}
	
	// 실패 함수 만들기
	// 패턴 내에서 접두사와 접미사가 일치하는 최대 길이를 계산
	private static int[] getPi(String pattern) {
		int m = pattern.length();
		int[] pi = new int[m];
		int j = 0;
		
		for (int i = 1; i < m; i++) {
			//일치하지 않으면 일치했던 이전 지점으로 점프
			while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			
			// 일치하면 길이를 1 증가시키고 pi 배열에 저장
			if(pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
}// end of class