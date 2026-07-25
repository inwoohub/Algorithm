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