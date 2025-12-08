import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] S; // 능력치 행렬
    static int minDiff = Integer.MAX_VALUE;

    /**
     * 주어진 팀의 능력치 합계를 계산합니다.
     * @param team 팀원들의 인덱스 (0부터 N-1) 리스트
     * @return 팀 능력치 합계
     */
    static int calculateTeamPower(List<Integer> team) {
        int power = 0;
        int size = team.size();
        // 팀원 2명을 고르는 모든 조합 (i, j)에 대해 S[i][j] 합산
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int personA = team.get(i);
                int personB = team.get(j);
                // S[A][B] + S[B][A]
                power += S[personA][personB];
                power += S[personB][personA]; 
            }
        }
        return power;
    }

    /**
     * DFS를 이용해 사람들을 스타트 팀에 포함시키거나 제외하며 모든 팀 구성을 탐색합니다.
     * @param index 현재 탐색 중인 사람의 인덱스 (0부터 N-1)
     * @param startTeam 스타트 팀에 현재까지 선택된 사람들의 인덱스 리스트
     * @param linkTeam 링크 팀에 현재까지 포함된 사람들의 인덱스 리스트
     */
    static void dfs(int index, List<Integer> startTeam, List<Integer> linkTeam) {
        // 1. 종료 조건: 모든 사람에 대한 선택을 완료했을 때
        if (index == N) {
            // 문제 조건: 두 팀 모두 최소 1명 이상이어야 함
            if (startTeam.size() > 0 && linkTeam.size() > 0) {
                int startPower = calculateTeamPower(startTeam);
                int linkPower = calculateTeamPower(linkTeam);
                
                int diff = Math.abs(startPower - linkPower);
                minDiff = Math.min(minDiff, diff);
            }
            return;
        }

        // 2. 재귀 호출: 현재 index의 사람을 스타트 팀에 포함하는 경우
        startTeam.add(index);
        dfs(index + 1, startTeam, linkTeam);
        // 백트래킹: 다음 탐색을 위해 추가했던 사람을 제거
        startTeam.remove(startTeam.size() - 1); 

        // 3. 재귀 호출: 현재 index의 사람을 링크 팀에 포함하는 경우
        linkTeam.add(index);
        dfs(index + 1, startTeam, linkTeam);
        // 백트래킹: 다음 탐색을 위해 추가했던 사람을 제거
        linkTeam.remove(linkTeam.size() - 1); 
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // N 입력
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        
        // 능력치 행렬 S 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 인덱스 0부터 N-1까지의 사람들을 두 팀으로 나누는 모든 경우를 탐색
        // 초기에는 두 팀 모두 비어있음
        dfs(0, new ArrayList<>(), new ArrayList<>());
        
        System.out.println(minDiff);
        
        br.close();
    }
}