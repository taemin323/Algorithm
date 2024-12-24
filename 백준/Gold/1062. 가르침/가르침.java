import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
 
    static int N, K;
    static int max = Integer.MIN_VALUE;
    static boolean[] visited;
    static String[] word;
    
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	
    	word = new String[N];
        
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            
            //anta와 tica 제거.
            str = str.replace("anta", "");
            str = str.replace("tica", "");
            word[i] = str;
        }
        
        if(K < 5) { //a c i n t의 개수가 5개이므로
            System.out.println(0);
            return;
        } else if(K == 26) { //모든 알파벳을 다 읽을 수 있다.
            System.out.println(N);
            return;
        }
        
        visited = new boolean[26]; //각 알파벳을 배웠는지 체크
        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        
        backtracking(0, 0);
        System.out.println(max);
    }
    
    public static void backtracking(int alpha, int len) {
        if(len == K - 5) {
            int cnt = 0;
            for(int i = 0; i < N; i++) { //뽑은 알파벳으로 몇개의 단어를 읽을 수 있는지 카운트.
                boolean flag = true;
                for(int j = 0; j < word[i].length(); j++) {
                    if(!visited[word[i].charAt(j) - 'a']) {
                        flag = false;
                        break;
                    }
                }
                if(flag) cnt++;
            }
            max = Math.max(max, cnt);
            return;
        }
        
        for(int i = alpha; i < 26; i++) {
            if(visited[i] == false) {
                visited[i] = true;
                backtracking(i, len + 1);
                visited[i] = false;
            }
        }
    }
}