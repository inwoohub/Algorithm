/**
알골리즘 :
    TSP (이미 풀어봤지만, 비트 마스킹 복기)

문제 요약 :
    최단 경로로 방문하기

전략 :
    1. 노드 간 연결 매핑 배열로 거리 저장
    2. mask 를 통해 모든 경우의 수 세기
    3. 

*/

import java.util.*;
import java.io.*;

public class Main{

    static int[][] dist; // 노드 간 거리
    static int N; // 노드의 개수
    static int[][] dp; // 비트마스킹할 dp 배열
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dist = new int[N][N];
        dp = new int[N][ (1<<N) ]; // N : 현재 위치, (1<<N) : 방문 상태
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], Integer.MAX_VALUE); // dp 배열 초기화
            for(int j=0; j<N; j++){
                int d = Integer.parseInt(st.nextToken());
                dist[i][j] = d;
            }
        }

        // DP 배열 초기값 세팅
        dp[0][(1<<0)] = 0; // 현재 위치는 0 이며, 0번 노드 방문시 크기는 0

        for(int mask=0; mask<(1<<N); mask++){ // 모든 방문 확인 용도
            
            for(int cur=0; cur<N; cur++){ // 현재 노드
                if( (mask&(1<<cur)) == 0  ) continue; // 현재 노드가 방문 경로 포함x
                if( dp[cur][mask] == Integer.MAX_VALUE ) continue; // 오버플로 방지 
                
                for(int next=0; next<N; next++){
                    if( (mask & (1<<next)) != 0 ) continue; // 이미 방문했으면 패스
                    if( dist[cur][next] == 0 ) continue; // 길 없음
                    int nextMask = (mask | (1<<next)); // 다음 방문 상태 마스크
                    dp[next][nextMask] = Math.min(dp[next][nextMask],
                                            dp[cur][mask] + dist[cur][next]); // 기존 vs 새로운 길
                }
            }
        }        

        int answer = Integer.MAX_VALUE;
        for(int next=1; next<N; next++){
            if(dist[next][0] == 0) continue; // 길 없음
            if(dp[next][(1<<N)-1]  == Integer.MAX_VALUE ) continue ; // 오버플로 방지
            answer = Math.min(answer, dp[next][(1<<N)-1] + dist[next][0] ); // 최소 값 찾기 근데 돌아야함
        }
        System.out.print(answer);
    }
}