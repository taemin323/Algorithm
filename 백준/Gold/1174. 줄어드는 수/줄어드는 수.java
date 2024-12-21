import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	 
	private static int N;
    static int [] arr = {9,8,7,6,5,4,3,2,1,0};
    static List<Long> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        N = Integer.parseInt(br.readLine());
 
        dfs(0,0);
        list.sort(null);
 
 
        if(N > list.size()) {
        	System.out.println(-1);
        } else {
        	System.out.println(list.get(N-1));
        }
    }
 
    private static void dfs(long num, int idx){
        if(idx == 10) {
        	if(!list.contains(num)) {
        		list.add(num);
        	}
        	return;
        }
    	
        // 해당 인덱스를 선택 안하고 넘어가.
        dfs((num*10)+arr[idx], idx+1);

        // 해당 인덱스를 선택한 경우 num에 더해준다.
        dfs(num,idx+1);
 
    }//end of main
}//end of class
