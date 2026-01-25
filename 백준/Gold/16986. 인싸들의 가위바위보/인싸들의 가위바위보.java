import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[][] A; // 상성 표
    static int[][] info = new int[3][20]; // 0: 지우, 1: 경희, 2: 민호
    static int[] pIdx = new int[3]; // 각 플레이어가 다음에 낼 손동작의 인덱스
    static int[] win = new int[3]; // 각 플레이어의 승수
    static boolean[] used = new boolean[10]; // 지우가 쓴 손동작 체크
    static int[] jiuMove = new int[10]; // 지우가 낼 손동작 순서

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 경희와 민호의 정보 입력 (1~20번째 손동작)
        for (int i = 1; i <= 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 20; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 지우의 순열을 만들고 게임 시작
        if (solve(0)) System.out.println(1);
        else System.out.println(0);
    }// end of main

    // 지우가 낼 수 있는 모든 순열을 백트래킹으로 생성
    static boolean solve(int depth) {
        if (depth == N) {
            return simulate();
        }

        for (int i = 1; i <= N; i++) {
            if (!used[i]) {
                used[i] = true;
                jiuMove[depth] = i;
                if (solve(depth + 1)) return true;
                used[i] = false;
            }
        }
        return false;
    }

    static boolean simulate() {
        // 게임 시작 전 초기화
        for (int i = 0; i < 3; i++) {
            win[i] = 0;
            pIdx[i] = 0;
        }
        // 지우의 손동작을 info 배열에 복사
        for (int i = 0; i < N; i++) info[0][i] = jiuMove[i];

        int p1 = 0, p2 = 1; // 처음은 지우(0)와 경희(1)가 경기
        int nextP = 2; // 쉬고 있는 사람 민호(2)

        while (true) {
            // 어느 한 명이라도 K승에 도달하면 종료
            if (win[0] == K) return true;
            if (win[1] == K || win[2] == K) return false;
            
            // 지우가 손동작을 다 썼는데 승리하지 못했다면 종료
            if (pIdx[0] >= N) return false;
            // 경희나 민호가 20번의 손동작을 다 썼다면 종료 (문제 조건상 K승 전엔 발생 가능성 낮음)
            if (pIdx[1] >= 20 || pIdx[2] >= 20) return false;

            int move1 = info[p1][pIdx[p1]++];
            int move2 = info[p2][pIdx[p2]++];

            int winner, loser;
            // 상성 체크: 2는 p1 승, 1은 무승부, 0은 p2 승
            if (A[move1][move2] == 2) {
                winner = p1; loser = p2;
            } else if (A[move1][move2] == 1) {
                winner = Math.max(p1, p2); // 번호가 큰 사람이 이김
                loser = Math.min(p1, p2);
            } else {
                winner = p2; loser = p1;
            }

            win[winner]++;
            // 경기자 교체
            int temp = nextP;
            nextP = loser;
            p1 = winner;
            p2 = temp;
        }
    }
}// end of class