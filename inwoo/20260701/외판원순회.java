// 문제
// 4개의 도시가 있다. 각 도시는 1번부터 4번까지 번호가 붙어 있다.
// 외판원은 한 도시에서 출발하여 모든 도시를 정확히 한 번씩 방문한 뒤, 다시 출발했던 도시로 돌아와야 한다.
// 도시 사이의 이동 비용은 아래 행렬 W로 주어진다.
// W[i][j]는 i번 도시에서 j번 도시로 이동하는 비용이다.
// 값이 0이면 자기 자신이거나, 이동할 수 없는 길이라는 뜻이다.
// 가장 적은 비용으로 모든 도시를 순회하는 비용을 구하시오.
// 정답 : 30

// ---------------------------------------------

// 알고리즘 : 비트 마스킹 + DP

// 전략 :
// 1. 배열 만들기 dp[][] : [현재 지점][방문 경로]
// 2. 배열 초기화 및 초기 세팅
// 3. 방문 안한 노드라면 방문 후 최소 거리로 갱신
// 4. 임의의 한점으로 출발지 설정 후 복귀 (서로 연결된 노드라 임의 한점 선택해도 싸이클 o)
// 5. 정답 출력

import java.io.*;
import java.util.*;

public class Main{

    static int N = 5;

    static int[][] W = {
            {0, 14, 4, 10, 20},
            {14, 0, 7, 8, 7},
            {4, 5, 0, 7, 16},
            {11, 7, 9, 0, 2},
            {18, 7, 17, 4, 0}
    };

    public static void main(String[] args) throws IOException{
        // 1. 배열 만들기
        int[][] dp = new int[N][(1<<N)];

        // 2. 배열 초기화 및 세팅
        for(int i=0; i<N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 3. 초기 배열 값 넣기
        dp[0][(1<<0)] = 0 ; // 현재 지점이 0이고, 방문한 노드가 0번 노드인 경우

        // 4. 3중 for문을 통해 mask 계산
        for(int mask=0; mask<(1<<N); mask++){
            for(int cur=0; cur<N; cur++){
                if( (mask&(1<<cur)) == 0 ) continue; // 현재 지점이 cur인데 방문경로에 cur이 포함x
                if( dp[cur][mask] == Integer.MAX_VALUE ) continue; // (+) 오버플로 방지
                for(int next=0; next<N; next++){
                    if( (mask&(1<<next)) != 0 ) continue; // 다음 경로 이미 방문완료
                    if( W[cur][next] == 0 ) continue; // 길 없는 경우
                    int nextMask = mask|(1<<next); // 다음 마스크
                    dp[next][nextMask] = Math.min(dp[next][nextMask],
                            dp[cur][mask] + W[cur][next]); // 더 가까운 경로로 갱신
                }
            }
        }

        // 5. 0번 노드로 복귀
        int answer = Integer.MAX_VALUE;
        for(int cur=1; cur<N; cur++){
            if(dp[cur][(1<<N)-1] == Integer.MAX_VALUE) continue; // 오버플로 방지
            answer = Math.min( answer, dp[cur][(1<<N)-1] + W[cur][0] );
        }
        System.out.println(answer);
    }
}