// ==============
// 알고리즘
// dfs + 메모이제이션
// ==============


import java.io.*;
import java.util.*;

public class Main{

    static int ans;
    static int N, M; // N: 세로, M: 가로
    static int[][] graph;
    static int[][] dp;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        dp = new int[N][M];
        for(int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<M; x++){
                graph[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 값 세팅
        for(int i=0; i<N; i++){
            Arrays.fill(dp[i], -1);
        }

        // 데이터 출력
        System.out.println( dfs(0,0,graph[0][0]) );

        // 테스트
        // for(int i=0; i<N; i++){
        //    for(int j=0; j<M; j++){
        //        System.out.print(dp[i][j]+" ");
        //    }
        //    System.out.println(" ");
        // }
        
    }

    static int dfs(int y, int x, int cur){

        // 1) 종료 조건
        if( y==N-1 && x==M-1 ){
            return 1;
        }
        
        // 2) 이미 방문했다면 return (메모이제이션)
        if( dp[y][x] != -1 ){
            return dp[y][x];
        } else {
            // 4) 첫 방문 시 dp[nextY][nextX] = -1 -> 0 변환
            dp[y][x] = 0;
            for(int i=0; i<4; i++){
                int nextY = y+dy[i];
                int nextX = x+dx[i];
    
                // 3) 그래프 범위 밖 -> 이동 불가
                if( nextY<0 || nextX<0 || nextY>N-1 || nextX>M-1 ) continue;

                // 5) 현재 값이 더 크다면 dp 값 갱신
                int next = graph[nextY][nextX];
                if(cur > next){
                    dp[y][x] += dfs(nextY, nextX, next);
                }
            }
        }

        return dp[y][x];
        
    }
}