// ---------------------------
// 알고리즘
// bitMask 를 활용한 dfs
// & 와 | 비트 연산자를 통해서 방문 여부 및 비트마스크 계산
// 싸이크링 존재하기 때문에 어느 곳에서 시작하더라도 상관없어서 0으로 시작.
// 
// 1. 비트마스킹을 통해 방문 했다면 통과
// 2. map 거리가 0 즉, 방문 못할시 통과
// 3. 현재 mask == maxMask 즉, 모든 정점 도달 했을 경우, return 마지막노드->0 으로 
// 4. 이미 방문했다라면, 바로 값 반환
// ---------------------------
// 주요 변수
// maxMask, map, dp
// ---------------------------

import java.io.*;
import java.util.*;


public class Main{

    static int N;
    static int maxMask;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        maxMask = (1<<N)-1;
        map = new int[N][N];
        dp = new int[N][maxMask+1];
        
        
        for(int i=0; i<N; i++){
            Arrays.fill(dp[i], -1);
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = dfsAndDP(0, 1);
        System.out.print(ans);
        
    }

    static int dfsAndDP(int cur, int mask){
        // 기존에 이미 방문했던거라면 바로 반환
        if(dp[cur][mask] != -1 ) return dp[cur][mask];
        
        if(mask == maxMask){
            // 방문 불가능
            if(map[cur][0] == 0){
                return 1000000000;
            }
            return map[cur][0];
        }

        int best = 1000000000;
        for(int next=0; next<N; next++){
            // 이미 방문했다면 통과
            if( (mask & ( 1<< next)) != 0 ) continue;
            // 길 없음
            if(map[cur][next] == 0 ) continue;

            int cand = map[cur][next] + dfsAndDP( next, mask | (1<<next) );

            best = Math.min(cand, best);
        }
        dp[cur][mask] = best;
        return best;
    }
}