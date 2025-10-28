import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] board;
	private static int timingMoney;
	private static int bnpMoney;
	private static int timingSum;
	private static int bnpSum;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int initMoney = Integer.parseInt(br.readLine());
		
		board = new int[14];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 14; i++) {
			board[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		timingMoney = initMoney;
		bnpMoney = initMoney;
		
		// 현재 보유 주식
		timingSum = 0;
		bnpSum = 0;
		
		
		// timing 방식
		int timingAns = TIMING();
		
		// bnp 방식
		int bnpAns = BNP();
		
		
		if(timingAns > bnpAns) System.out.println("TIMING");
		else if(timingAns < bnpAns) System.out.println("BNP");
		else System.out.println("SAMESAME");
	}//end of main

	private static int BNP() {
		for(int i = 0; i < 14; i++) {
			if(bnpMoney >= board[i]) {
				int canBuy = bnpMoney / board[i];
				bnpMoney -= board[i] * canBuy;
				bnpSum += canBuy;
			}
		}
		
		return board[13] * bnpSum + bnpMoney;
	}
	
	private static int TIMING() {
		int upCnt = 0;
		int downCnt = 0;
		
		for(int i = 1; i < 14; i++) {
			if(board[i-1] < board[i]) {
				upCnt++;
				downCnt = 0;
			} else if(board[i-1] > board[i]) {
				downCnt++;
				upCnt = 0;
			} else {
				upCnt = 0;
				downCnt = 0;
			}
			
			// 하락장(매수)
			if(downCnt >= 3) {
				int canBuy = timingMoney / board[i];
				
				if(canBuy > 0) {
					timingSum += canBuy;
					timingMoney -= canBuy * board[i];
				}
			}
			
			// 상승장(매도)
			if(upCnt >= 3 && timingSum > 0) {
				timingMoney += timingSum * board[i];
				timingSum = 0;
			}
		}
		return board[13]* timingSum + timingMoney;
	}
}//end of class
