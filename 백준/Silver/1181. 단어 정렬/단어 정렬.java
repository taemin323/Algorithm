import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Set<String> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			if(!set.contains(word)) {
				set.add(word);
			}
		}// 입력 완료
		
		String[] words = new String[set.size()];
		int idx = 0;
		for (String word : set) {
			words[idx++] = word;
		}
		
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				// 단어가 같을 경우
				if(s1.length() == s2.length()) {
					return s1.compareTo(s2);// 사전 순 정렬
				}
				// 그 외의 경우
				else {
					return s1.length() - s2.length();
				}
			}
		});
		
		for (String word : words) {
			System.out.println(word);
		}
		
	}// end of main
}// end of class
