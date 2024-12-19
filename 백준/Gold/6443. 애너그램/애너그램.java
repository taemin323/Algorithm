import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static String str;
	private static char[] word;
	private static StringBuilder sb;
	private static char[] selected;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());//단어의 개수
		
		
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			word = new char[str.length()];
			selected = new char[str.length()];
			visited = new boolean[str.length()];
			
			for (int j = 0; j < str.length(); j++) {
				word[j] = str.charAt(j);
			}
			Arrays.sort(word);
			perm(0);
		}
		System.out.println(sb.toString());
	}//end of main

	private static void perm(int cnt) {
		if(cnt == word.length) {
			for (int i = 0; i < word.length; i++) {
				sb.append(selected[i]);
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < word.length; i++) {
			
			if(i > 0 && word[i] == word[i-1] && !visited[i-1]) continue;
			
			if(!visited[i]) {
				visited[i] = true;
				selected[cnt] = word[i];
				perm(cnt+1);
				visited[i] = false;
			}
		}
	}

}//end of class
