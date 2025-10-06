import java.util.*;

class Solution {
    private static class Info {
        char friend1, friend2, op;
        int len;
        Info(char friend1, char friend2, char op, int len) {
            this.friend1 = friend1;
            this.friend2 = friend2;
            this.op = op;
            this.len = len;
        }
    }

    private static final char[] FRIENDS = {'A','C','F','J','M','N','R','T'};
    private static boolean[] visited = new boolean[8];
    private static int[] pos = new int[8];   // FRIENDS[i]가 선 자리(미배치:-1)
    private static Info[] list;
    private static int answer;

    public int solution(int n, String[] data) {
        list = new Info[n];
        for (int i = 0; i < n; i++) {
            String s = data[i];
            list[i] = new Info(s.charAt(0), s.charAt(2), s.charAt(3), s.charAt(4) - '0');
        }
        Arrays.fill(visited, false);
        Arrays.fill(pos, -1);
        answer = 0;

        perm(0);
        return answer;
    }

    private static void perm(int depth) {
        if (depth == 8) {
            answer++;
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            pos[i] = depth;              // FRIENDS[i]를 depth 자리에 세움

            if (check()) {            
                perm(depth + 1);
            }

            pos[i] = -1;                 // 원복
            visited[i] = false;
        }
    }

    // 현재까지 배치된 사람들만 가지고 제약 위반 여부 즉시 검사
    private static boolean check() {
        for (Info i : list) {
            int ai = idx(i.friend1);
            int bi = idx(i.friend2);
            int a = pos[ai];
            int b = pos[bi];
            if (a == -1 || b == -1) continue;     // 둘 다 배치된 경우만 판단

            int gap = Math.abs(a - b) - 1;        // 끼어 있는 사람 수
            if (i.op == '=' && gap != i.len) return false;
            if (i.op == '<' && gap >= i.len) return false;
            if (i.op == '>' && gap <= i.len) return false;
        }
        return true;
    }

    // 문자 → FRIENDS 인덱스(0..7)
    private static int idx(char c) {
        switch (c) {
            case 'A': return 0; case 'C': return 1; case 'F': return 2; case 'J': return 3;
            case 'M': return 4; case 'N': return 5; case 'R': return 6; case 'T': return 7;
        }
        return -1;
    }
}
