import java.util.*;
/**
* 3:33 - 4:33 실패 / 5:00
* 10점 ~1점까지의 각각의 개수가 정해져 있음.
* 라이언이 못이기는 경우 -> [-1]
* 가장 큰 점수차로 이기려면 높은 점수를 되도록 많이 가져와야됨
* 해당 점수의 화살 개수가 같은 경우엔 무조건 어피치가 점수 가져옴
* 라이언이 가장 큰 점수차이로 우승할 수 있는 방법이 여러 가지라면
* -> 가장 낮은 점수를 더 많이 맞힌 경우를 return
* 10,9,8,7 중에서 뺏어올 점수 + 다른 점수 이게 합쳐서 뺏어오고 남은 점수보다 커야돼.
* n을 고려해서.
* 만약에 1,2,3,4이라면? 굳이 점수 뺏어올 필요도 없음. 가장 큰 점수차는 10에 때려박으면 되니까.
* 즉 고려할 게 너무 많아 지금. 결국 완탐으로 해야된다는 소리.
* 가져오고 싶은 점수를 미리 정해? 해당 점수를 가져오려면 info[i] + 1을 투자해야돼 -> 라이언 점수 += (10-i)
* n -= info[i]+1
* dfs로 접근 가능?
* 기저 조건 n == 0이 되면 result 배열은 해당 점수마다 개수가 기록되어 있을거고, 이제 이 result랑 info를 비교해서 라이언과 어피치 점수를 매겨. 그리고 차이도 저장해. 그런 다음 return을 때려.
* !visited[i] && info[i] >= n -> result[i] = info[i]+1
* 
* 1. 기저조건 오류
*   여기서는 과녁 인덱스 (0부터 10까지) 기준으로 탐색해야하며 idx==1에 도달했을 때 남은 화살(n)을 모두 0점에 몰아주고 점수를 계산
* 2. 점수 계산 시 0점 가져오는 로직 오류
*   peach += (10-i)로 계산 -> 라이언과 어피치 둘 다 0발 맞힌 과녁도 가져옴. -> 어피치가 점수를 얻으려면 info[i] > 0 조건 추가.
* 3. 가장 낮은 점수를 더 많이 맞힌 경우 갱신 조건 젤 어려워,.
*   현재는 gap <= lion - peach일 때 단순히 win.add(result)만 하고 있음. 이렇게 하면 점수차가 같을 때 가장 낮은 점수를 더 많이 맞힌 조합으로 갱신 못함.
* 
* 결국 각 과녁(10~0점)에 대해 라이언은 딱 두 가지 선택만 하면 됨.
* 이긴다 : 어피치보다 1발 더 쏘기 -> info[i] + 1
* 진다 : 0발 
* 힌트 : .clone() 활용하기
*/

class Solution {
    int maxGap = Integer.MIN_VALUE;
    int[] answer = {-1};
    int[] lion = new int[11];
    
    public int[] solution(int n, int[] info) {
        dfs(0,n,info);
        
        return answer;
    }
    
    void dfs(int idx, int n, int[] info) {
        if(idx == 11) {
            //남은 화살이 있다면 모두 0에 몰아넣기
            lion[10] += n;
            
            calculate(info);
            
            //원복
            lion[10] -= n;
            return;
        }
        
        //선택 1: 해당 점수를 가져오겠다.
        if(n >= info[idx] + 1) {
            lion[idx] += info[idx] + 1;
            dfs(idx+1, n - lion[idx], info);
            lion[idx] = 0;//원복
        }
        
        //선택 2: 해당 점수 포기
        dfs(idx+1, n, info);
    }
    
    void calculate(int[] info) {
        int lionScore = 0;
        int peachScore = 0;
        
        for(int i = 0; i< 11; i++) {
            if(info[i] == 0 && lion[i] == 0) continue;
            
            if(lion[i] > info[i]) lionScore += (10-i);
            else peachScore += (10-i);
        }
        
        int gap = lionScore - peachScore;
        
        if(gap > 0) {
            //더 큰 점수차라면 무조건 갱신
            if(gap > maxGap) {
                maxGap = gap;
                answer = lion.clone();
            } else if(gap == maxGap) {
                if(isBetter(lion, answer)) {
                    answer = lion.clone();
                }
            }
        }
    }
    
    boolean isBetter(int[] newLion, int[] curLion) {
        for(int i = 10; i >= 0; i--) {
            if(newLion[i] != curLion[i]) return newLion[i] > curLion[i];
        }
        
        return false;
    }
}