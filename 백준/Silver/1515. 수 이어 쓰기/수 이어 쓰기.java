import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int idx = 0;
		for (int i = 1; ; i++) {
			String num = String.valueOf(i);
			for (int j = 0; j < num.length() && idx < str.length(); j++) {
				if(num.charAt(j) == str.charAt(idx)) {
					idx++;
				}
			}
			if(idx == str.length()) {
				System.out.println(i);
				break;
			}
		}
		
	}// end of main
}// end of class
