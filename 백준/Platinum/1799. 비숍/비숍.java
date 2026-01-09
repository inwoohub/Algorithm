// ------------------
// 알고리즘
// dfs + backtracking 
// ------------------

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] wChess;
    static int[][] bChess;
    static int[][] visited;
    static int[] dx = {-1, -1, 1, 1};
    static int[] dy = {-1, 1, -1, 1};
    static int wMax = 0;
    static int bMax = 0;
    
    public static void main(String[] args) throws IOException{
        // 데이터 매핑
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        wChess = new int[N][N];
        bChess = new int[N][N];
        visited = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                if( (i+j)%2 == 0 ){
                    bChess[i][j] = Integer.parseInt(st.nextToken());
                } else {
                    wChess[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        } // 데이터 매핑 끝

        // black칸 white 칸 구분 시간복잡도 ↓
        bdfs(0, bChess, 0);
        wdfs(0, wChess, 0);
        System.out.print(bMax+wMax);

    }

    static void go(int x, int y, int[][] chess){
        // 체스판 크기 내에서 방문하지 않고, 벽을 만나기 전까지 방문 표기
        for(int i=0; i<4; i++){ 
            int cx = x;
            int cy = y;
            // 보드판에 범위 내에서만 while 문 실행
            while( cx+dx[i]>=0 && cx+dx[i]<N && cy+dy[i]>=0 && cy+dy[i]<N ){
                cx += dx[i];
                cy += dy[i];
                visited[cx][cy]++; // 해당 자리 공격
            }
        }
    }

    static void reverseGo(int x, int y, int[][] chess){
        for(int i=0; i<4; i++){
            int cx = x;
            int cy = y;
            while( cx+dx[i]>=0 && cx+dx[i]<N && cy+dy[i]>=0 && cy+dy[i]<N ){
                cx += dx[i];
                cy += dy[i];
                visited[cx][cy]--; // 해당 자리 공격 풀어주기
            }
        }
    }

    static void bdfs(int idx, int[][] chess, int count){
        // 현재 카운트와 최대 값 비교
        bMax = Math.max(bMax, count);

        // 인덱스로 관리
        // k/N = 행이되고, k%N = 열이 됨
        for(int k = idx; k < N*N; k++){
            int i = k / N; // 행
            int j = k % N; // 열

            // 1. 놓을 수 없는 칸이라면, 계속 진행
            if(chess[i][j] == 0) continue;

            // 2. 놓을 수 없고 다른 말에 의해 공격받는 다면 놓을 수 없음
            if(visited[i][j] != 0) continue;

            // 1,2 둘 다 아닌 경우 현재 자리 점유
            // 대각선 go 함수를 통해 모두 공격
            // 현재 위치에서 dfs 시작
            // reverseGo 함수를 통해 공격 풀어주기 (백트래킹)
            // 현재 자리 점유 해제
            visited[i][j]++;      // 자기 자리도 점유
            go(i, j, chess);
            bdfs(k + 1, chess, count + 1);
            reverseGo(i, j, chess);
            visited[i][j]--;
        }
    }

    static void wdfs(int idx, int[][] chess, int count){
        wMax = Math.max(wMax, count);
    
        for(int k = idx; k < N*N; k++){
            int i = k / N; // 행
            int j = k % N; // 열
    
            if(chess[i][j] == 0) continue;     // 놓을 수 없는 칸
            if(visited[i][j] != 0) continue;  // 공격받는 칸
    
            visited[i][j]++;      // 자기 자리도 점유
            go(i, j, chess);
            wdfs(k + 1, chess, count + 1);
            reverseGo(i, j, chess);
            visited[i][j]--;
        }
    }
}