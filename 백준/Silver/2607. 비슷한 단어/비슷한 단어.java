import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	private static int[] base;
	private static int ans;
	private static int baseLength;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		
		base = new int[26];
		for (int i = 0; i < words[0].length(); i++) {
			char c = words[0].charAt(i);
			base[c - 65]++;
		}
		
		ans = 0;
		baseLength = words[0].length();
		for (int i = 1; i < N; i++) {
			calculate(words[i]);
		}
		
		System.out.println(ans);
		
	}// end of main

	private static void calculate(String word) {
		int[] compare = new int[26];
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			compare[c - 65]++;
		}
		
		int dif = 0;
		for (int i = 0; i < 26; i++) {
			dif += Math.abs(base[i] - compare[i]);
		}
		
		int lenDif = Math.abs(baseLength - word.length());
		
		if(lenDif > 1) return;
		
		if(lenDif == 0 && (dif == 0 || dif == 2)) ans++;
		else if(lenDif == 1 && dif == 1) ans++;
		
	}	
}// end of class
