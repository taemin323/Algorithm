import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 어떤 문자를 정확히 K개를 포함하고 있는 가장 짧은 연속 문자열의 길이
 * 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이.
 */
public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			String W = br.readLine();
			int K = Integer.parseInt(br.readLine());
			
			// 각 알파벳에 대한 인덱스 저장
			List<Integer>[] alphaIdx = new ArrayList[26];
			for (int i = 0; i < 26; i++) {
				alphaIdx[i] = new ArrayList<>();
			}
			
			// 각 문자 인덱스를 리스트에 저장
			for (int i = 0; i < W.length(); i++) {
				char c = W.charAt(i);
				alphaIdx[c- 'a'].add(i);
			}
			
			int minLen = Integer.MAX_VALUE;
			int maxLen = Integer.MIN_VALUE;
			
			// 각 알파벳에 대해 슬라이딩 윈도우 진행
			for (int i = 0; i < 26; i++) {
				List<Integer> list = alphaIdx[i];
				if(list.size() < K) continue;
				
				for (int j = 0; j <= list.size() - K; j++) {
					int start = list.get(j);
					int end = list.get(j + K - 1);
					int len = end - start + 1;
					
					minLen = Math.min(minLen, len);
					maxLen = Math.max(maxLen, len);
				}
			}
			
			if(minLen == Integer.MAX_VALUE) {
				sb.append(-1);
			} else {
				sb.append(minLen).append(" ").append(maxLen);
			}
			sb.append("\n");
		}// end of tc
		System.out.println(sb.toString());
	}// end of main

}//end of class
